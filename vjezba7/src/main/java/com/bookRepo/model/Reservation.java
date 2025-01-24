package com.bookRepo.model;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Long id;
    private String reservationDate;
    private String reservationTime;
    private String reservationStatus;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId",nullable = false)
    private Member member;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId",nullable = false)
    private Book book;

    public Long getBookId() {
        return book.getId();
    }
    public Long getMemberId() {
        return member.getId();
    }
    public String getBookTitle() {
        return book.getTitle();
    }
}
