package ru.kirphone.timetable.database.updatetimetable.parser.pdf;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import ru.kirphone.timetable.database.TimeTableEntity;
import ru.kirphone.timetable.database.updatetimetable.parser.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class PdfParser extends Parser {

    private PDDocument document;

    protected PDDocument getDocument(){
        return document;
    }

    public PdfParser(InputStream inputData) throws IOException {
        super(inputData);

        try {
            document = PDDocument.load(inputData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
