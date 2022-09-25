package com.rtszh.books.service.implementation;

import com.rtszh.books.dto.AuthorBySymbolDto;
import com.rtszh.books.dto.BookDto;
import com.rtszh.books.factories.BookFactory;
import com.rtszh.books.model.Book;
import com.rtszh.books.repository.BooksRepository;
import com.rtszh.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BooksRepository booksRepository;

    @Autowired
    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public List<BookDto> getAllBooksOrderedByTitleDesc() {
        var booksList = booksRepository.findAll();

        return booksList.stream()
                .map(BookFactory::createBookFullDto)
                .sorted(Comparator.comparing(BookDto::getTitle).reversed())
                .toList();
    }

    @Override
    public void addBook(BookDto bookDto) {

        booksRepository.save(
                Book.builder()
                        .author(bookDto.getAuthor())
                        .title(bookDto.getTitle())
                        .description(bookDto.getDescription())
                        .build()
        );
    }

    @Override
    public Map<String, List<BookDto>> getAllBooksGroupByAuthors() {
        var booksList = booksRepository.findAll();

        return booksList.stream()
                .map(BookFactory::createBookFullDto)
                .collect(
                        Collectors.groupingBy(BookDto::getAuthor)
                );
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AuthorBySymbolDto> getAuthorsBySymbols(String symbol) {
        var booksList = booksRepository.getAuthorsAndTitles();

        var authorTitleMap = booksList.stream()
                .collect(
                        Collectors.groupingBy(
                                Book::getAuthor,
                                Collectors.mapping(
                                        Book::getTitle,
                                        Collectors.toList()
                                )
                        )
                );

        var authorCountMap = authorTitleMap.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().stream()
                                        .map(String::toLowerCase)
                                        .map(title -> StringUtils.countOccurrencesOf(title, symbol.toLowerCase()))
                                        .reduce(0, Integer::sum)
                        )
                );


        var authorCountMapWithoutEmpty = new HashMap<String, Integer>();

        authorCountMap.forEach((author, count) -> {
            if (count != 0) {
                authorCountMapWithoutEmpty.put(author, count);
            }
        });

        return authorCountMapWithoutEmpty.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .map(entry ->
                        BookFactory.createAuthorBySymbolDto(entry.getKey(), entry.getValue())
                )
                .limit(10)
                .toList();
    }
}
