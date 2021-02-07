package ru.kirphone.timetable.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kirphone.timetable.database.TimeTableEntity;
import ru.kirphone.timetable.database.TimeTablesRepository;
import ru.kirphone.timetable.database.updatetimetable.UpdateTimeTableManager;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api")
public class TimeTableController {

    private TimeTablesRepository timeTablesRepository;

    @Autowired
    public TimeTableController(TimeTablesRepository timeTablesRepository){
        this.timeTablesRepository = timeTablesRepository;
    }

    @GetMapping(path = "/timetable/educationalInstitutions")
    public ResponseEntity getAllEducationalInstitutions(){
        return ok(timeTablesRepository.findAllEducationalInstitutions());
    }

    @GetMapping(path = "/timetable/educationalInstitutions/{educationalInstitution}/groups")
    public ResponseEntity getAllGroupsByEducationalInstitution(@PathVariable("educationalInstitution") String educationalInstitution){
        return ok(timeTablesRepository.findAllGroupsByEducationalInstitution(educationalInstitution));
    }

    @GetMapping(path = "/timetable/educationalInstitutions/{educationalInstitution}/groups/{groupName}")
    public ResponseEntity getNewTimeTable(@PathVariable("educationalInstitution") String educationalInstitution,
                                       @PathVariable("groupName") String groupName){
        List<TimeTableEntity> timetable = timeTablesRepository.findAllByEducationalInstitutionAndGroupName(educationalInstitution, groupName);
        timetable.removeIf(item -> (item.getDate().isBefore(LocalDate.now())));
        return ok(timetable);
    }
}