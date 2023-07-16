package com.makers.TechVerse.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "enroll")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String fullName, phoneNumber;
}
