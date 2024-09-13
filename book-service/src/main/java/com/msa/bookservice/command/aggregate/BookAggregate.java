package com.msa.bookservice.command.aggregate;

import com.msa.bookservice.command.command.CreateBookCommand;
import com.msa.bookservice.command.command.DeleteBookCommand;
import com.msa.bookservice.command.command.UpdateBookCommand;
import com.msa.bookservice.command.event.BookCreatedEvent;
import com.msa.bookservice.command.event.BookDeletedEvent;
import com.msa.bookservice.command.event.BookUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
@Getter
@Setter
public class BookAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    @CommandHandler
    private BookAggregate(CreateBookCommand createBookCommand) {
        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
        BeanUtils.copyProperties(createBookCommand, bookCreatedEvent);
        AggregateLifecycle.apply(bookCreatedEvent);
    }

    @CommandHandler
    private void handle(UpdateBookCommand updateBookCommand) {
        BookUpdatedEvent bookUpdatedEvent = new BookUpdatedEvent();
        BeanUtils.copyProperties(updateBookCommand, bookUpdatedEvent);
        AggregateLifecycle.apply(bookUpdatedEvent);
    }

    @CommandHandler
    private void handle(DeleteBookCommand deleteBookCommand) {
        BookDeletedEvent bookDeletedEvent = new BookDeletedEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeletedEvent);
        AggregateLifecycle.apply(bookDeletedEvent);
    }

    @EventSourcingHandler
    public void on(BookCreatedEvent bookCreatedEvent) {
        this.id = bookCreatedEvent.getId();
        this.name = bookCreatedEvent.getName();
        this.author = bookCreatedEvent.getAuthor();
        this.isReady = bookCreatedEvent.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookUpdatedEvent bookUpdatedEvent) {
        this.id = bookUpdatedEvent.getId();
        this.name = bookUpdatedEvent.getName();
        this.author = bookUpdatedEvent.getAuthor();
        this.isReady = bookUpdatedEvent.getIsReady();
    }
    @EventSourcingHandler
    public void on(BookDeletedEvent bookDeletedEvent) {
        this.id = bookDeletedEvent.getId();
    }

}
