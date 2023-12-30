package com.hostfully.book.controller;

import com.hostfully.book.controller.dto.BookDTO;
import com.hostfully.book.hexagono.domain.Book;
import com.hostfully.book.hexagono.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping(value = "/book")
public class BookController {
    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @GetMapping("{id}")
    public ResponseEntity<BookDTO>  findBook(@PathVariable Long id){
        Book currentBook = bookService.findBookById(id);

        if (currentBook==null) {
            System.out.println("Restaurant with id " + id + " not found");
            return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(currentBook, HttpStatus.OK);
    }

    @PutMapping("cancel/{id}")
    public ResponseEntity <BookDTO> cancelBook(@PathVariable Long id) {
        Book currentBook = bookService.findBookById(id);

        if (currentBook==null) {
            System.out.println("Restaurant with id " + id + " not found");
            return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.cancelBook(id), HttpStatus.OK);
    }

    @PutMapping("rebook/{id}")
    public ResponseEntity <BookDTO> rebookBook(@PathVariable Long id) {
        Book currentBook = bookService.findBookById(id);

        if (currentBook==null) {
            System.out.println("Restaurant with id " + id + " not found");
            return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.rebookBook(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <BookDTO> updateBook(@PathVariable Long id,@RequestBody BookDTO bookDTObody) {
        Book currentBook = bookService.findBookById(id);

        if (currentBook==null) {
            System.out.println("Restaurant with id " + id + " not found");
            return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.updateBook(id,bookDTObody), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("book")
    public ResponseEntity <BookDTO> book(@RequestParam String guestName, @RequestParam Date initialDate, @RequestParam Date finalDate){
        return new ResponseEntity<>(bookService.book(guestName,initialDate,finalDate), HttpStatus.CREATED);
    }





}
