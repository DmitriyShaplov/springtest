package ru.shaplov.spring.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "child")
public class ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
}
