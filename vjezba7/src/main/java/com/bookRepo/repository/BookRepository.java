package com.bookRepo.repository;

import com.bookRepo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
    @Query("SELECT s FROM Book s WHERE s.title = ?1")
    Optional<Book> findByTitle(String title);

    //bez queria naci
    @Query("SELECT b FROM Book b WHERE " +
            "(LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')) OR :title IS NULL) AND " +
            "(LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')) OR :author IS NULL) AND " +
            "(LOWER(b.genre) = LOWER(:genre) OR :genre IS NULL) AND " +
            "(b.publishedYear = :publishedYear OR :publishedYear IS NULL)")
    Page<Book> searchAndFilter(String title, String author, String genre, Integer publishedYear, Pageable pageable);

}
