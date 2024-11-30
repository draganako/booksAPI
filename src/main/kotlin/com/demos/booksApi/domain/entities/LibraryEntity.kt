package com.demos.booksApi.domain.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.*

@Entity
@Table(name = "libraries")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
data class LibraryEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_id_seq")
    val id: Long?,

    val description: String?,

    val image: String?,

    val name: String,

    val location: String,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "library_book",
        joinColumns = [JoinColumn(name = "library_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "book_isbn", referencedColumnName = "isbn")]
    )
    @JsonManagedReference
    var books : List<BookEntity> = listOf()
)