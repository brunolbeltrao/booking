package com.hostfully.book.hexagono.Adapter;

import com.hostfully.book.hexagono.domain.Guest;
import com.hostfully.book.repository.GuestEntity;
import com.hostfully.book.repository.GuestRepository;
import com.hostfully.book.repository.GuestRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class GuestRepositoryAdapter implements GuestRepository {

    private final GuestRepositoryJpa guestRepositoryJpa;

    protected GuestRepositoryAdapter(GuestRepositoryJpa guestRepositoryJpa) {
        this.guestRepositoryJpa = guestRepositoryJpa;
    }

    public Guest save(Guest guest) {
        GuestEntity guestEntity = new GuestEntity(guest.getName());
        return build(guestRepositoryJpa.save(guestEntity));
    }

    public void delete(Guest Guest) {
        guestRepositoryJpa.deleteById(Guest.getId());
    }

    @Override
    public Guest findById(Long id) {
        GuestEntity guestEntity = guestRepositoryJpa.findById(id).get();
        Guest guest = build(guestEntity);
        return guest;
    }

    private Guest build(GuestEntity guestEntity) {
        if (guestEntity== null){
            return null;
        }
        Guest guest = Guest.builder().id(guestEntity
                        .getId())
                .name(guestEntity.getName())
                .build();

        return guest;
    }



    private List<Guest> buildListGuests(List<GuestEntity> guestEntitys) {
        List<Guest> guests =  new ArrayList<>();

        guestEntitys.stream().forEach(guestEntity -> {
            Guest guest = new Guest(guestEntity.getId(),guestEntity.getName());
            guests.add(guest);
        });

        return guests;
    }


    public Guest findGuestById(Long id) {
        GuestEntity guestEntity = guestRepositoryJpa.findById(id).get();
        return build(guestEntity);
    }

    public List<Guest> findGuests() {
        return buildListGuests(guestRepositoryJpa.findAll());
    }

    public Guest findGuestByName(String name) {
        GuestEntity guestEntity = guestRepositoryJpa.findGuestByName(name);
        return build(guestEntity);
    }
}
