package com.msa.bookservice.command.data;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
