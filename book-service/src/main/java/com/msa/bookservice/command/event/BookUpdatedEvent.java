package com.msa.bookservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookUpdatedEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
