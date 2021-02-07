package ru.kirphone.timetable.database.updatetimetable.parser.excel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jxl.Cell;
import lombok.Data;
import ru.kirphone.timetable.database.TimeTableEntity;
import ru.kirphone.timetable.database.updatetimetable.parser.ParserException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class RanepaSpbExcelParser extends ExcelParser {

    public RanepaSpbExcelParser(InputStream inputData) throws IOException {
        super(inputData);
    }

    @Override
    public List<TimeTableEntity> parse() throws ParserException {
        try {

            List<TimeTableEntity> resultList = new ArrayList<>();

            for (int i = 1; i < getSheet().getRows(); ++i) {
                if (getSheet().getRow(i).length == 0)
                    continue;

                List<TimeTableEntity> timeTableEntities;

                try {
                    timeTableEntities = parseGroupsName(getSheet().getRow(i)[4].getContents());
                } catch (StringIndexOutOfBoundsException ex){
                    continue;
                }

                for (TimeTableEntity timeTableEntity : timeTableEntities) {
                    parseOneRowWithoutGroupName(getSheet().getRow(i), timeTableEntity);
                    resultList.add(timeTableEntity);
                }
            }

            return resultList;

        } catch (IllegalStateException ex) {
            throw new ParserException();
        }
    }

    private void parseOneRowWithoutGroupName(Cell[] row, TimeTableEntity currentEntity){

        currentEntity.setDate(LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(row[2].getContents()),
                Integer.parseInt(row[1].getContents())));


        currentEntity.setEducationalInstitution("ranepa_spb");
        currentEntity.setSubjectName(row[6].getContents());
        currentEntity.setType(row[5].getContents());

        String[] timeDiapasone = row[3].getContents().trim().split("-");

        if (timeDiapasone.length == 0) {
            currentEntity.setType("self");
        } else if (timeDiapasone.length == 2) {
            currentEntity.setStartTime(getLocalTimeFromString(timeDiapasone[0]));
            currentEntity.setEndTime(getLocalTimeFromString(timeDiapasone[1]));

            setOtherProperties(row, currentEntity);
        }
    }

    private List<TimeTableEntity> parseGroupsName(String groupName) {

        List<TimeTableEntity> timeTableEntities = new ArrayList<>();

        if(groupName.contains(",")){
            String[] groupsNames = groupName.split(",");
            String groupNamePrefix = groupsNames[0].substring(0, groupsNames[0].length() - 2);
            timeTableEntities.add(TimeTableEntity.builder().groupName(groupsNames[0]).build());
            for(int i = 1; i < groupsNames.length; ++i){
                timeTableEntities.add(TimeTableEntity.builder().groupName(groupNamePrefix + groupsNames[i]).build());
            }
        } else{
            timeTableEntities.add(TimeTableEntity.builder().groupName(groupName).build());
        }

        editTimeTableEntitiesWithLanguageGroup(timeTableEntities);

        return timeTableEntities;
    }

    private void editTimeTableEntitiesWithLanguageGroup(List<TimeTableEntity> timeTableEntities){
        String lastGroupName = timeTableEntities.get(timeTableEntities.size() - 1).getGroupName();
        Character languageGroup = Character.toLowerCase(lastGroupName.charAt(lastGroupName.length() - 1));
        Gson gson = new GsonBuilder().create();

        if (languageGroup >= 'a' && languageGroup <= 'z' || languageGroup >= 'а' && languageGroup <= 'я') {
            Note note = new Note();
            note.setSpecial("Только для группы " + languageGroup);
            note.setOther("");
            for (TimeTableEntity timeTableEntity : timeTableEntities) {
                timeTableEntity.setNote(gson.toJson(note));
            }

            timeTableEntities.get(timeTableEntities.size() - 1).setGroupName(lastGroupName.substring(0, lastGroupName.length() - 1));
        }
    }

    private void setOtherProperties(Cell[] row, TimeTableEntity timeTable){
        timeTable.setTeacherName(row[7].getContents());
        timeTable.setClassroom(row[8].getContents());
        Gson gson = new GsonBuilder().create();
        Note note = gson.fromJson(timeTable.getNote(), Note.class);
        if(note == null)
            note = new Note();
        if(row.length >= 10)
            note.setOther(row[9].getContents());
        else
            note.setOther("");
        timeTable.setNote(gson.toJson(note));
    }


    private LocalTime getLocalTimeFromString(String time){
        return LocalTime.of(Integer.parseInt(time.split("\\.")[0]),
                Integer.parseInt(time.split("\\.")[1]));
    }

    @Data
    private class Note {

        private String special;
        private String other;
    }
}
