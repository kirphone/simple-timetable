package ru.kirphone.timetable.database.updatetimetable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirphone.timetable.database.TimeTableEntity;
import ru.kirphone.timetable.database.TimeTablesRepository;
import ru.kirphone.timetable.database.updatetimetable.downloader.Downloader;
import ru.kirphone.timetable.database.updatetimetable.parser.Parser;
import ru.kirphone.timetable.database.updatetimetable.parser.ParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UpdateTimeTableManager {

    private final Logger logger = LoggerFactory.getLogger(UpdateTimeTableManager.class);

    private final DownloadsRepository downloadsRepository;

    private final TimeTablesRepository timeTablesRepository;

    private Integer numberOfUpdate = 0;
    private Long startTime = 0L;
    private Integer countOFDownloads;
    private static final int delay = 3600000;

    @Autowired
    public UpdateTimeTableManager(DownloadsRepository downloadsRepository, TimeTablesRepository timeTablesRepository){
        this.downloadsRepository = downloadsRepository;
        this.timeTablesRepository = timeTablesRepository;
    }

    @Scheduled(fixedDelay = delay)
    public void updateTimeTables(){
        List<DownloadEntity> downloads = downloadsRepository.findAll();
        countOFDownloads = downloads.size();
        logger.info("Началась загрузка документов и обновление таблицы расписания");
        startTime = System.currentTimeMillis();

        downloads.forEach(item -> new Thread(() -> {
            try {
                updateOneDownload(item);
            } catch (ParserException | ReflectiveOperationException | IOException | IllegalStateException ex) {
                ex.printStackTrace();
                logger.error("link = {}", item.getLink());
                logger.error("groupListName = {}", item.getGroupListName());
                logger.error(ex.getMessage());
            }
        }).start());

    }

    private void updateOneDownload(DownloadEntity downloadEntity) throws ReflectiveOperationException, IOException, ParserException {
        Downloader downloader = (Downloader) getAdditionalClass(downloadEntity.getEducationalInstitution(), downloadEntity.getFormat(), "Downloader")
                .getConstructor(String.class).newInstance(downloadEntity.getLink());

        InputStream data = new BufferedInputStream(downloader.downloadOneTimeTable());

        Parser parser = (Parser) getAdditionalClass(downloadEntity.getEducationalInstitution(), downloadEntity.getFormat(), "Parser")
                .getConstructor(InputStream.class).newInstance(data);
        transferToDB(parser.parse());
        numberOfUpdate++;
        if(numberOfUpdate % countOFDownloads == 0)
            logger.info("Закончилось обновление таблицы расписания. Оно заняло {}ms", System.currentTimeMillis() - startTime);

    }

    @Transactional
    public void transferToDB(List<TimeTableEntity> transferredData){
        Set<String> deletedGroups = new HashSet<>();
        transferredData.forEach(item -> {
            if(!deletedGroups.contains(item.getGroupName())){
                timeTablesRepository.deleteAllByEducationalInstitutionAndGroupName(item.getEducationalInstitution(), item.getGroupName());
                deletedGroups.add(item.getGroupName());
            }
        });
        timeTablesRepository.saveAll(transferredData);
    }

    private Class<?> getAdditionalClass(String educationalInstitution, String format, String classBaseName) throws ReflectiveOperationException{
        StringBuilder className = new StringBuilder();
        className.append(educationalInstitution);
        className.setCharAt(0, Character.toUpperCase(className.charAt(0)));
        removeUnderscore(className);

        StringBuilder formatBuilder = new StringBuilder(format);
        formatBuilder.setCharAt(0, Character.toUpperCase(formatBuilder.charAt(0)));
        removeUnderscore(formatBuilder);
        className.append(formatBuilder).append(classBaseName);

        StringBuilder packageBuilder = new StringBuilder();
        formatBuilder.setCharAt(0, Character.toLowerCase(formatBuilder.charAt(0)));
        packageBuilder.append(classBaseName).append(".").append(formatBuilder).append(".");
        packageBuilder.setCharAt(0, Character.toLowerCase(packageBuilder.charAt(0)));
        return Class.forName("ru.kirphone.timetable.database.updatetimetable." + packageBuilder + className);
    }

    private void removeUnderscore(StringBuilder str){
        int lastIndex = 0;
        while(lastIndex != -1){
            lastIndex = str.indexOf("_");
            if(lastIndex != -1){
                str.setCharAt(lastIndex + 1, Character.toUpperCase(str.charAt(lastIndex + 1)));
                str.deleteCharAt(lastIndex);
            }
        }
    }
}
