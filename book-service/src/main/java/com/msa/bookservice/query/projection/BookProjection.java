package com.msa.bookservice.query.projection;

import com.msa.bookservice.command.data.Book;
import com.msa.bookservice.command.data.BookRepository;
import com.msa.bookservice.query.model.BookResponseModel;
import com.msa.bookservice.query.queries.GetAllBookQuery;
import com.msa.bookservice.query.queries.GetBookDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookProjection {

    private final BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery getAllBookQuery) {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookResponseModel::new).toList();
    }

    @QueryHandler
    public BookResponseModel handle(GetBookDetailQuery getBookDetailQuery) {
        Book book = bookRepository.findById(getBookDetailQuery.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return new BookResponseModel(book);
    }
}
