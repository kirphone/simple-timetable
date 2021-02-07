package ru.kirphone.timetable.database.updatetimetable.parser.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import ru.kirphone.timetable.database.updatetimetable.parser.Parser;

import java.io.*;
import java.util.Locale;

public abstract class ExcelParser extends Parser {

    private Sheet sheet;
    protected Sheet getSheet() {
        return sheet;
    }

    public ExcelParser(InputStream inputData) throws IOException {
        super(inputData);
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setEncoding("Cp1251");
            sheet = Workbook.getWorkbook(inputData, ws).getSheet(0);
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
