package ru.kirphone.timetable.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeTablesRepository extends JpaRepository<TimeTableEntity, Long> {

    @Transactional
    @Modifying
    void deleteAllByGroupName(String groupName);

    @Transactional
    @Modifying
    void deleteAllByEducationalInstitutionAndGroupName(String educationalInstitution, String groupName);

    List<TimeTableEntity> findAllByEducationalInstitutionAndGroupName(String educationalInstitution, String groupName);

    @Query("select distinct t.educationalInstitution from TimeTableEntity as t")
    List<String> findAllEducationalInstitutions();

    @Query("select distinct t.groupName from TimeTableEntity as t where t.educationalInstitution=:educationalInstitution")
    List<String> findAllGroupsByEducationalInstitution(String educationalInstitution);
}
