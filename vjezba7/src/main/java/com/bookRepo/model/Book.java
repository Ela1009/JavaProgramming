package com.bookRepo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "books")
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private int publishedYear;
    private int pages;

    public Book(String title,String author,String genre,String publisher,int publishedYear,int pages){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.pages = pages;
    }

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

}
