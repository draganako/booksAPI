package com.demos.booksApi.domain.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.*

@Entity
@Table(name="books")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
data class BookEntity(
    @Id
    @Column(name="isbn")
    val isbn: String,

    @Column(name="title")
    val title: String,

    @Column(name="description")
    val description: String,

    @Column(name="image")
    val image: String,

    @ManyToOne(cascade = [CascadeType.DETACH])
    @JoinColumn(name="author_id")
    val authorEntity: AuthorEntity,

    @ManyToMany(mappedBy = "books")
    //@JsonBackReference
    val libraries : List<LibraryEntity> = listOf()
)