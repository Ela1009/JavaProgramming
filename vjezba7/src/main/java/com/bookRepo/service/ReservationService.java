package com.bookRepo.service;

import com.bookRepo.exception.BookNotFoundException;
import com.bookRepo.model.*;
import com.bookRepo.model.Book;
import com.bookRepo.model.Reservation;
import com.bookRepo.repository.BookRepository;
import com.bookRepo.repository.MemberRepository;
import com.bookRepo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ReservationService {
     private final ReservationRepository reservationRepository;
     private final NotificationService notificationService;
     private final BookRepository bookRepository;
     private final MemberRepository memberRepository;



    @Autowired
    public ReservationService(ReservationRepository reservationRepository, NotificationService notificationService, BookRepository bookRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.notificationService = notificationService;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public void createReservation(Reservation reservation) {
        Reservation reservation1 = new Reservation();
        reservation1.setReservationDate(LocalDate.now().toString());
        reservation1.setReservationTime(LocalTime.now().toString());
        reservation1.setReservationStatus("Pending");

        Book book= fetchBookById(reservation.getBookId());
        Member member = fetchMemberById(reservation.getMemberId());

        reservation1.setBook(book);
        reservation1.setMember(member);
        reservationRepository.save(reservation);
    }

    public void fulfillReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Reservation not found"));
            reservation.setReservationStatus("Fulfilled");
            reservationRepository.save(reservation);
            String message = String.format("Knjiga %s je sada dostupna za posudbu.", reservation.getBook().getTitle());
            notificationService.createNotification(reservation.getMember(),message);
    }
    private Book fetchBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with ID " + bookId + " not found"));
    }

    private Member fetchMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member with ID " + memberId + " not found"));
    }

}
