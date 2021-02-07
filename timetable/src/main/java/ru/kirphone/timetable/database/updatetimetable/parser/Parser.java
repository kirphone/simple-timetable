package ru.kirphone.timetable.database.updatetimetable.parser;

import ru.kirphone.timetable.database.TimeTableEntity;

import java.io.InputStream;
import java.util.List;

public abstract class Parser {

    private InputStream inputData;

    public InputStream getInputData(){
        return inputData;
    }

    protected void setInputData(InputStream inputData){
        this.inputData = inputData;
    }

    public Parser(InputStream inputData){
        this.inputData = inputData;
    }

    public abstract List<TimeTableEntity> parse();
}
