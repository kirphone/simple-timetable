package ru.kirphone.timetable.database.updatetimetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadsRepository extends JpaRepository<DownloadEntity, Long> {

    List<DownloadEntity> findAll();
}
