package ru.kirphone.timetable.database.updatetimetable.downloader.excel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import ru.kirphone.timetable.database.updatetimetable.downloader.Downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RanepaSpbExcelDownloader extends Downloader {

    public RanepaSpbExcelDownloader(String url) {
        super(url);
    }

    @Override
    public InputStream downloadOneTimeTable() throws IOException {
            Element el = Jsoup.connect(getUrl()).get().selectFirst("script");

            Pattern pattern = Pattern.compile("\"FileGetUrl\":\".*?\"");
            String text = el.data().replaceAll("\\\\u0026", "&");
            Matcher matcher = pattern.matcher(text);
            matcher.find();
            String finded = matcher.group();
            String res = finded.substring(14, finded.length() - 1);

            return downloadData(res);
    }
}
