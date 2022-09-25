package com.rtszh.books.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorBySymbolDto {
    private String author;
    private int count;
}
