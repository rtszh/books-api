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
                title = "Books service api",
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
    public ResponseEntity<?> getAllBooksOrderedByTitleDesc() {
        var bookList = bookService.getAllBooksOrderedByTitleDesc();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/by-author")
    public ResponseEntity<?> getAllBooksGroupByAuthors() {
        var bookMap = bookService.getAllBooksGroupByAuthors();

        return ResponseEntity.ok(bookMap);
    }

    @GetMapping("/by-symbol")
    public ResponseEntity<?> getAuthorsBySymbols(
            @RequestParam String symbol
    ) {

        var authorsMap = bookService.getAuthorsBySymbols(symbol);

        return ResponseEntity.ok(authorsMap);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {

        bookService.addBook(bookDto);

        return ResponseEntity.ok().build();
    }
}
