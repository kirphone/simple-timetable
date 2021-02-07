package ru.kirphone.timetable.database.updatetimetable.downloader;

import lombok.AllArgsConstructor;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

@AllArgsConstructor
public abstract class Downloader {

    private String url;

    protected String getUrl() {
        return url;
    }

    public abstract InputStream downloadOneTimeTable() throws IOException;

    protected InputStream downloadData(String url) throws IOException {
        URL urlAddress = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlAddress.openConnection();
        connection.setRequestMethod("GET");
        return connection.getInputStream();
    }
}
