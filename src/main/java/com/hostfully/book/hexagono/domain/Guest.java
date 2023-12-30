package com.hostfully.book.hexagono.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Guest {
    private Long id;
    private String name;

    public boolean validBook(){
        //impement validations
        return true;
    }

}
