package com.hostfully.book.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="block")
public class BlockEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date initialBlock;
    private Date finalBlock;

    public BlockEntity() {
    }

    public BlockEntity(Long id, Date initialBlock, Date finalBlock) {
        this.id = id;
        this.initialBlock = initialBlock;
        this.finalBlock = finalBlock;
    }
}
