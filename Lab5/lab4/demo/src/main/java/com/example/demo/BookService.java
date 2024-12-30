package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getBooks(String title, String author, String genre, Integer publishedYear, Pageable pageable) {
        return bookRepository.findAllByFilters(title, author, genre, publishedYear, pageable);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setPublishedYear(updatedBook.getPublishedYear());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
