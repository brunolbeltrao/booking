package com.hostfully.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface GuestRepositoryJpa extends JpaRepository <GuestEntity, Long> {

    @Query("select s from GuestEntity s where s.name LIKE %:name% ")
    GuestEntity findGuestByName(@Param("name") String name);
}

