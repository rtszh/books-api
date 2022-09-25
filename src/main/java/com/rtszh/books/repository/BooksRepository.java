package com.rtszh.books.repository;


import com.rtszh.books.model.Book;

import java.util.List;

public interface BooksRepository {
    List<Book> findAll();

    List<Book> getAuthorsAndTitles();

    void save(Book bookDto);
}
