package com.hostfully.book.controller.dto;

import com.hostfully.book.hexagono.domain.Book;
import com.hostfully.book.hexagono.domain.Guest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private Guest guest;
    private Date initialDate;
    private Date finalDate;

    public static BookDTO build(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .guest(book.getGuest())
                .initialDate(book.getInitialDate())
                .finalDate(book.getFinalDate())
                .build();
    }
}
