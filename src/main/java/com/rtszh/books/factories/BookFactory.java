package com.rtszh.books.factories;

import com.rtszh.books.dto.AuthorBySymbolDto;
import com.rtszh.books.dto.BookDto;
import com.rtszh.books.model.Book;

public class BookFactory {

    public static BookDto createBookFullDto(Book book) {
        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .build();
    }

    public static AuthorBySymbolDto createAuthorBySymbolDto(String author, int count) {
        return AuthorBySymbolDto.builder()
                .author(author)
                .count(count)
                .build();
    }

}
