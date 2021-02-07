package ru.kirphone.timetable.database.updatetimetable.parser.pdf.text;

import org.apache.pdfbox.text.PDFTextStripper;
import ru.kirphone.timetable.database.TimeTableEntity;
import ru.kirphone.timetable.database.updatetimetable.parser.pdf.PdfParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class PdfTextParser extends PdfParser {

    private String text;

    protected String getText(){
        return text;
    }

    public PdfTextParser(InputStream inputData) throws IOException {
        super(inputData);

        text = new PDFTextStripper().getText(getDocument());
    }
}
