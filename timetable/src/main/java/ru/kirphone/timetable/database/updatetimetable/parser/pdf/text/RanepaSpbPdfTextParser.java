package ru.kirphone.timetable.database.updatetimetable.parser.pdf.text;

import org.apache.pdfbox.text.PDFTextStripper;
import ru.kirphone.timetable.database.TimeTableEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RanepaSpbPdfTextParser extends PdfTextParser {

    public RanepaSpbPdfTextParser(InputStream inputData) throws IOException {
        super(inputData);
    }

    @Override
    public List<TimeTableEntity> parse() {

        List<TimeTableEntity> resultList = new ArrayList<>();

        String a = getText();

        return resultList;
    }
}
