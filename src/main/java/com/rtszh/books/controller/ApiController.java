package com.rtszh.books.controller;

import com.rtszh.books.dto.BookDto;
import com.rtszh.books.service.BookService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@OpenAPIDefinition(
        info = @Info(
                title = "books service api",
                description = "Test",
                version = "0.1.1"
        )
)
public class ApiController {

    private final BookService bookService;

    @Autowired
    public ApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
//    @Operation(summary = "Get all books ordered by title descending",
//            description = "endpoint возвращающий список все книг, которые содержатся в таблице book, отсортированный в обратном алфавитном порядке значения колонки book.title"
//    )
    public ResponseEntity<?> getAllBooksOrderedByTitleDesc() {
        var bookList = bookService.getAllBooksOrderedByTitleDesc();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/by-author")
//    @Operation(summary = "Get all books by authors",
//            description = "endpoint возвращающий список всех книг, сгруппированных по автору книги(book.author)"
//    )
    public ResponseEntity<?> getAllBooksGroupByAuthors() {
        var bookMap = bookService.getAllBooksGroupByAuthors();

        return ResponseEntity.ok(bookMap);
    }

    @GetMapping("/by-symbol")
//    @Operation(summary = "Get all books by symbols",
//            description = "endpoint принимающий в качестве параметра символ и возвращающий список из 10 авторов, " +
//                    "в названии книг которых этот символ встречается наибольшее количество раз " +
//                    "вместе с количеством вхождений этого символа во все названия книг автора"
//    )
    public ResponseEntity<?> getAuthorsBySymbols(
            @RequestParam String symbol
    ) {

        var authorsMap = bookService.getAuthorsBySymbols(symbol);

        return ResponseEntity.ok(authorsMap);
    }

    @PostMapping
//    @Operation(summary = "Add new book to database",
//            description = "endpoint добавления новой книги в таблицу book"
//    )
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {

        bookService.addBook(bookDto);

        return ResponseEntity.ok().build();
    }
}
