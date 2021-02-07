package ru.kirphone.timetable.database.updatetimetable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "downloads")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DownloadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grouplist_name", nullable = false)
    private String groupListName;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "format", nullable = false)
    private String format;

    @Column(name = "educational_institution", nullable = false)
    private String educationalInstitution;
}
