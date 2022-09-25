package com.rtszh.books;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BooksApplicationTests {
    private final ApplicationContextRunner context = new ApplicationContextRunner();

    @Test
    void contextLoads() {
        context.run(
                it -> assertThat(it).hasNotFailed()
        );
    }

}
