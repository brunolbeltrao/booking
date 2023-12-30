package com.hostfully.book.hexagono.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private Long id;
    private Guest guest;
    private Date initialDate;
    private Date finalDate;
    private State state;
    public enum State {
        ACTIVE, CANCELED
    }

    public boolean validBook(){
        //impement validations
        return true;
    }
}
