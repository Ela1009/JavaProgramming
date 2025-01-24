package com.bookRepo.controller;

import com.bookRepo.model.Book;
import com.bookRepo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping
//    public List<Book> getBooks(@RequestParam(value = "pageNum",defaultValue = "0",required = false) int pageNum,
//                               @RequestParam(value = "pageSize",defaultValue = "2",required = false) int pageSize,
//                               @RequestParam(value = "sort",defaultValue = "id",required = false) String sort)            {
//        return bookService.getAllBooks(pageNum,pageSize,sort);
//    }


//hashmap
    @GetMapping
    public List<Book> searchBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "publishedYear", required = false) Integer publishedYear,
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "title,asc", required = false) String[] sort) {
        return bookService.searchBooks(title, author, genre, publishedYear, pageNum, pageSize, sort);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }
    @DeleteMapping(path = "{Id}")
    public void deleteBook(@PathVariable("Id") Long Id) {
        bookService.deleteBook(Id);
    }

    @PutMapping(path = "{Id}")
    public void updateBook(@PathVariable("Id") Long Id, @RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        bookService.updateBook(Id,title,author);
    }

}
