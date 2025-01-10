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
@Table(name = "members")
public class Member {
    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

}
