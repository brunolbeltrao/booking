package com.hostfully.book.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="guest")
public class GuestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public GuestEntity() {
    }

    public GuestEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GuestEntity(String name) {
        this.name = name;
    }
}
