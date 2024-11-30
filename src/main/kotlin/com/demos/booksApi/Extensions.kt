package com.demos.booksApi

import com.demos.booksApi.domain.*
import com.demos.booksApi.domain.dto.*
import com.demos.booksApi.domain.entities.AuthorEntity
import com.demos.booksApi.domain.entities.BookEntity
import com.demos.booksApi.domain.entities.LibraryEntity
import com.demos.booksApi.exceptions.InvalidAuthorException

fun AuthorEntity.toAuthorDto() = AuthorDto(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image
)

fun AuthorEntity.toAuthorSummaryDto(): AuthorSummaryDto{
    val authorId = this.id ?: throw InvalidAuthorException()
    return AuthorSummaryDto(
        id = authorId,
        name = this.name,
        image = this.image,
    )
}

fun AuthorDto.toAuthorEntity() = AuthorEntity(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image
)

fun AuthorUpdateRequestDto.toAuthorUpdateRequest() = AuthorUpdateRequest(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image
)

fun AuthorSummaryDto.toAuthorSummary() = AuthorSummary(
    id = this.id,
    name = this.name,
    image = this.image
)

fun BookNoLibsDto.toBookRequest() = BookNoLibs(
    isbn=this.isbn,
    title=this.title,
    description = this.description,
    image = this.image,
    authorSummary = this.authorSummaryDto.toAuthorSummary()
)

fun BookNoLibs.toBookEntity(author: AuthorEntity) = BookEntity(
    isbn=this.isbn,
    title=this.title,
    description = this.description ?: "",
    image = this.image ?: "",
    authorEntity = author
)

fun BookEntity.toBookSummaryDto() = BookSummaryDto(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    author = authorEntity.toAuthorSummaryDto(),
    libraries = mutableListOf<LibraryEntity>().apply { addAll(libraries) }
)

fun BookEntity.toBookRequestDto() = BookNoLibsDto(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    authorSummaryDto = authorEntity.toAuthorSummaryDto(),
)

fun BookUpdateRequestDto.toBookUpdateRequest() = BookUpdateRequest(
    title = this.title,
    description = this.description,
    image = this.image
)

fun LibraryEntity.toLibraryDto() = LibraryDto(
    id = this.id,
    name = this.name,
    location = this.location,
    description = this.description,
    image = this.image,
    books = mutableListOf<BookNoLibsDto>().apply { addAll(books.map { it.toBookRequestDto() }) }
)

fun LibraryCreateRequestDto.toLibraryEntity() = LibraryEntity(
    id = this.id,
    name = this.name,
    location = this.location,
    description = this.description,
    image = this.image
)

fun LibraryUpdateRequestDto.toLibraryUpdateRequest() = LibraryUpdateRequest(
    id = this.id,
    name = this.name,
    location = this.location,
    description = this.description,
    image = this.image
)