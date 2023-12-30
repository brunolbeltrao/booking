package com.hostfully.book.hexagono.Adapter;


import com.hostfully.book.controller.dto.BookDTO;
import com.hostfully.book.hexagono.domain.Book;
import com.hostfully.book.hexagono.domain.Guest;
import com.hostfully.book.repository.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class BookRepositoryAdapter implements BookRepository {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final GuestRepositoryJpa guestRepositoryJpa;

    public BookRepositoryAdapter(BookRepositoryJpa bookRepositoryJpa, GuestRepositoryJpa guestRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.guestRepositoryJpa = guestRepositoryJpa;
    }

    public Book save(Book book) {
        return build(bookRepositoryJpa.save(buildEntity(book)));
    }

    public Book cancel(Book book) {
        Book currentBook = findBookById(book.getId());

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setGuest(buildGuestEntity(currentBook.getGuest()));
        bookEntity.setInitialDate(currentBook.getInitialDate());
        bookEntity.setFinalDate(currentBook.getFinalDate());
        bookEntity.setState(book.getState().toString());

        return build(bookRepositoryJpa.save(bookEntity));
    }

    public Book rebook(Book book) {
        Book currentBook = findBookById(book.getId());

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setGuest(buildGuestEntity(currentBook.getGuest()));
        bookEntity.setInitialDate(currentBook.getInitialDate());
        bookEntity.setFinalDate(currentBook.getFinalDate());
        bookEntity.setState(book.getState().toString());

        return build(bookRepositoryJpa.save(bookEntity));
    }

    public void delete(Book book) {
        bookRepositoryJpa.deleteById(book.getId());;
    }

    private Book build(BookEntity bookEntity) {
        return  Book.builder()
                .id(bookEntity.getId())
                .initialDate(bookEntity.getInitialDate())
                .finalDate(bookEntity.getFinalDate())
                .guest(build(bookEntity.getGuest()))
                .build();
    }

    private BookEntity buildEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setGuest(buildGuestEntity(book.getGuest()));
        bookEntity.setInitialDate(book.getInitialDate());
        bookEntity.setFinalDate(book.getFinalDate());
        return bookEntity;
    }

    private Guest build(GuestEntity guestEntity) {
        if (guestEntity==null){
            return null;
        }
        return Guest.builder().id(guestEntity.getId())
                .name(guestEntity.getName()).build();
    }

    private GuestEntity buildGuestEntity(Guest guest){
        if (guest == null){
            return  null;
        }
        return new GuestEntity(guest.getId(),guest.getName());
    }

    private List<Book> buildListBook(List<BookEntity> bookEntities) {
        List<Book> books =  new ArrayList<>();

        bookEntities.stream().forEach(bookEntity -> {
            Book book = new Book(bookEntity.getId(),build(bookEntity.getGuest()),
                    bookEntity.getInitialDate(), bookEntity.getFinalDate(),Book.State.ACTIVE);
            books.add(book);
        });

        return books;
    }

    @Override
    public Book findById(Long id) {
        BookEntity bookEntity = bookRepositoryJpa.findById(id).get();
        Book book = build(bookEntity);
        return book;
    }

    public Book findBookById(Long id) {
        Optional<BookEntity> bookOptional = bookRepositoryJpa.findById(id);

        if (bookOptional.isEmpty()){
            return  null;
        }
        Book book = build(bookOptional.get());
        return book;
    }

    public BookDTO book(Guest guest, Book book) {
        BookEntity bookEntity = new BookEntity(buildGuestEntity(book.getGuest()), book.getInitialDate(), book.getFinalDate(),book.getState().toString());

        bookRepositoryJpa.save(bookEntity);

        return(BookDTO.builder().id(bookEntity.getId()).initialDate(bookEntity.getInitialDate()).finalDate(bookEntity.getFinalDate())
                .guest(build(bookEntity.getGuest())).build());
    }

    public List<Book> findBooks() {
        return buildListBook(bookRepositoryJpa.findAll());
    }
}

