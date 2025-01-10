package com.bookRepo.book;

import com.bookRepo.model.*;
import com.bookRepo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

    @Configuration
    public class BookConfig {

        @Bean
        CommandLineRunner runner(BookRepository bookRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
            return args -> {
                Book bookA = new Book("Babel", "R.F.Kuang", "Fantasy", "Harpercollins", 2023, 546);
                Book bookB = new Book("No Longer Human", "Osamu Dazai", "Fiction", "New Directions", 1973, 176);
                Book bookC = new Book("Hyperion", "Dan Simmons", "Science Fiction", "Doubleday", 1989, 482);
                Book bookD = new Book("Mistborn: The Final Empire", "Brandon Sanderson", "Fantasy", "Tor Books", 2006, 541);
                Book bookE = new Book("Mistborn: The Well of Ascension", "Brandon Sanderson", "Fantasy", "Tor Books", 2007, 590);
                Book bookF = new Book("Mistborn: The Hero of Ages", "Brandon Sanderson", "Fantasy", "Tor Books", 2008, 572);

                bookRepository.saveAll(List.of(bookA, bookB, bookC, bookD, bookE, bookF));

                Member member1 = new Member();
                member1.setName("John Doe");
                member1.setEmail("john.doe@example.com");
                member1.setPhone("1234567890");

                Member member2 = new Member();
                member2.setName("Jane Smith");
                member2.setEmail("jane.smith@example.com");
                member2.setPhone("0987654321");

                memberRepository.saveAll(List.of(member1, member2));

                Reservation reservation1 = new Reservation();
                reservation1.setReservationDate(LocalDate.now().toString());
                reservation1.setReservationTime(LocalTime.now().toString());
                reservation1.setReservationStatus("Pending");
                reservation1.setBook(bookA);
                reservation1.setMember(member1);

                Reservation reservation2 = new Reservation();
                reservation2.setReservationDate(LocalDate.now().toString());
                reservation2.setReservationTime(LocalTime.now().toString());
                reservation2.setReservationStatus("Pending");
                reservation2.setBook(bookB);
                reservation2.setMember(member2);

                reservationRepository.saveAll(List.of(reservation1, reservation2));
            };
        }
    }