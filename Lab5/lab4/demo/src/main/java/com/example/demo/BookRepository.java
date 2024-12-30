package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR b.title LIKE %:title%) AND " +
            "(:author IS NULL OR b.author LIKE %:author%) AND " +
            "(:genre IS NULL OR b.genre = :genre) AND " +
            "(:publishedYear IS NULL OR b.publishedYear = :publishedYear)")
    Page<Book> findAllByFilters(@Param("title") String title,
                                @Param("author") String author,
                                @Param("genre") String genre,
                                @Param("publishedYear") Integer publishedYear,
                                Pageable pageable);
}
