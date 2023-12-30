package com.hostfully.book.repository;

import com.hostfully.book.hexagono.domain.Guest;

public interface GuestRepository {

    void delete(Guest student);
    Guest findById(Long id);
}
