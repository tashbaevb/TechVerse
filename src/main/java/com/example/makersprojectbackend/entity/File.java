package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.entity.course.Course;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.MERGE;

@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_seq")
    @SequenceGenerator(name = "file_seq", sequenceName = "file_seq", allocationSize = 1)
    private Long id;

    private String fileName;

    private String type;

    private byte[] file;

    @OneToOne(cascade = {MERGE, DETACH})
    private Course course;

}
