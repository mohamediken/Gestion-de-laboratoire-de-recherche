package com.iken.Labo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String auteur;

    @Temporal(TemporalType.DATE)
    private LocalDate publicationDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}

