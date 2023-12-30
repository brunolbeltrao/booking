package com.hostfully.book.hexagono.service;

import com.hostfully.book.controller.dto.BookDTO;
import com.hostfully.book.hexagono.Adapter.BookRepositoryAdapter;
import com.hostfully.book.hexagono.Adapter.GuestRepositoryAdapter;
import com.hostfully.book.hexagono.domain.Book;
import com.hostfully.book.hexagono.domain.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class BookService {
    @Autowired
    private BookRepositoryAdapter bookRepositoryAdapter;

    @Autowired
    private GuestRepositoryAdapter guestRepositoryAdapter;

    public BookDTO updateBook(Long idBook,BookDTO bookDTO) {
        Book book = Book.builder()
                .id(idBook)
                .guest(bookDTO.getGuest())
                .initialDate(bookDTO.getInitialDate())
                .finalDate(bookDTO.getFinalDate())
                .build();
        return BookDTO.build(bookRepositoryAdapter.save(book));
    }

    public BookDTO cancelBook(Long idBook) {
        Book book = Book.builder()
                .id(idBook)
                .state(Book.State.CANCELED)
                .build();
        return BookDTO.build(bookRepositoryAdapter.cancel(book));
    }

    public BookDTO rebookBook(Long idBook) {
        Book book = Book.builder()
                .id(idBook)
                .state(Book.State.ACTIVE)
                .build();
        return BookDTO.build(bookRepositoryAdapter.rebook(book));
    }

    public Book findBookById(Long id) {
        return bookRepositoryAdapter.findBookById(id);
    }

    public void delete(Long id) {
        Book book = bookRepositoryAdapter.findBookById(id);
        bookRepositoryAdapter.delete(book);
    }
    private Guest solveGuast(String guestName){
        Guest guest = guestRepositoryAdapter.findGuestByName(guestName);
        if(guest != null){
            return guest;
        }else{
            return guestRepositoryAdapter.save(Guest.builder().name(guestName).build());
        }
    }

    public BookDTO book(String guestName, Date initialDate, Date finalDate) {
        Guest guest = solveGuast(guestName);

        Book book = Book.builder()
                    .guest(guest)
                    .initialDate(initialDate)
                    .finalDate(finalDate)
                    .state(Book.State.ACTIVE)
                    .build();

        book.validBook();
        guest.validBook();

        return bookRepositoryAdapter.book(guest, book);
    }


    public List<BookDTO> Books() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookRepositoryAdapter.findBooks();

        books.stream().forEach(book -> {
            bookDTOS.add(BookDTO.builder()
                    .id(book.getId())
                    .guest(book.getGuest())
                    .initialDate(book.getInitialDate())
                    .finalDate(book.getFinalDate())
                    .build());
        });
        return bookDTOS;
    }
}
