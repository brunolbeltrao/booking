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
public class Block {
    private Long id;
    private Date initialBlock;
    private Date finalBlock;

}
