package com.example.redis_test.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "TEST_USER")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "NICKNAME")
    private String nickname;
}
