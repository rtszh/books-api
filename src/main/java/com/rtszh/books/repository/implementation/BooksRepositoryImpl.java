package com.rtszh.books.repository.implementation;

import com.rtszh.books.model.Book;
import com.rtszh.books.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksRepositoryImpl implements BooksRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(
                "select * from book",
                (rs, rowNum) ->
                        Book.builder()
                                .id(rs.getLong("id"))
                                .title(rs.getString("title"))
                                .author(rs.getString("author"))
                                .description(rs.getString("description"))
                                .build()
        );
    }

    @Override
    public List<Book> getAuthorsAndTitles() {
        return jdbcTemplate.query(
                "select title, author from book",
                (rs, rowNum) ->
                        Book.builder()
                                .title(rs.getString("title"))
                                .author(rs.getString("author"))
                                .build()
        );
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update(
                "insert into book(id, title, author, description) values(?, ?, ?, ?)",
                book.getId(), book.getTitle(), book.getAuthor(), book.getDescription()
        );
    }
}
