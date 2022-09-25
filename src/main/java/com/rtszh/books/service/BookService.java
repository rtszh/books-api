package com.rtszh.books.service;

import com.rtszh.books.dto.AuthorBySymbolDto;
import com.rtszh.books.dto.BookDto;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<BookDto> getAllBooksOrderedByTitleDesc();

    void addBook(BookDto bookDto);

    Map<String, List<BookDto>> getAllBooksGroupByAuthors();

    List<AuthorBySymbolDto> getAuthorsBySymbols(String symbol);
}
