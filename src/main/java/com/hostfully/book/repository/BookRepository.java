package com.hostfully.book.repository;

import com.hostfully.book.hexagono.domain.Book;

public interface BookRepository {

    void delete(Book book);
    Book findById(Long id);
}
