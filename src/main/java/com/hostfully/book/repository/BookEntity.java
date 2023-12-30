package com.hostfully.book.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="book")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gest_id")
    private GuestEntity guest;
    private Date initialDate;
    private Date finalDate;

    private String state;

    public BookEntity() {
    }

    public BookEntity(GuestEntity guest, Date initialDate, Date finalDate, String state) {
        this.guest = guest;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.state = state;
    }
}
