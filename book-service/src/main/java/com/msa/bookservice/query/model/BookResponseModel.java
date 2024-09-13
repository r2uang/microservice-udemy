package com.msa.bookservice.query.model;

import com.msa.bookservice.command.data.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class BookResponseModel {

    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    public BookResponseModel(Book book){
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.isReady = book.getIsReady();
    }
}
