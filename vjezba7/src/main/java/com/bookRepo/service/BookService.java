package com.bookRepo.service;

import com.bookRepo.model.Book;
import com.bookRepo.repository.BookRepository;
import com.bookRepo.exception.BookNotFoundException;
import com.bookRepo.exception.InvalidRequestException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


//    public List<Book> getAllBooks(int pageNumber, int pageSize, String sort) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort));
//        Page<Book> books = bookRepository.findAll(pageable);
//        return books.getContent();
//    }

    public List<Book> searchBooks(String title, String author, String genre, Integer publishedYear, int pageNumber, int pageSize, String[] sort) {
        String sortField = sort[0];
        String sortDirection = sort.length > 1 ? sort[1] : "asc";
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sortBy = Sort.by(direction, sortField);

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortBy);
        Page<Book> books = bookRepository.searchAndFilter(title, author, genre, publishedYear, pageable);
        return books.getContent();
    }

    public void addNewBook(Book book) {
        System.out.println(book);
        Optional<Book> BookByTitle = bookRepository.findByTitle(book.getTitle());
        if (BookByTitle.isPresent()) {
            throw new InvalidRequestException("Book already exists");
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new BookNotFoundException("Book with the id " + bookId +" does not exist");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String title, String author) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
        new BookNotFoundException("Book with the id " + bookId + " does not exist"));
        if (title != null && !title.isEmpty() && !Objects.equals(book.getTitle(), title)) {
            Optional<Book> BookByTitle = bookRepository.findByTitle(title);
            if (BookByTitle.isPresent()) {
                throw new InvalidRequestException("Book already exists");
            }
            book.setTitle(title);
        }
        if (author != null && !author.isEmpty() && !Objects.equals(book.getAuthor(), author)) {
            book.setAuthor(author);
        }
    }

}
