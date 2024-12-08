package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Dohvaćanje svih knjiga
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Dohvaćanje knjige prema ID-u
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Spremanje nove knjige
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Ažuriranje postojeće knjige
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPrice(updatedBook.getPrice());
        return bookRepository.save(existingBook);
    }

    // Brisanje knjige prema ID-u
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
