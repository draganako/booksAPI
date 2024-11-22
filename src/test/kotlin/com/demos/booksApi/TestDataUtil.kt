package com.demos.booksApi
import com.demos.booksApi.domain.AuthorSummary
import com.demos.booksApi.domain.AuthorUpdateRequest
import com.demos.booksApi.domain.BookSummary
import com.demos.booksApi.domain.dto.AuthorDto
import com.demos.booksApi.domain.dto.AuthorSummaryDto
import com.demos.booksApi.domain.dto.AuthorUpdateRequestDto
import com.demos.booksApi.domain.dto.BookSummaryDto
import com.demos.booksApi.domain.entities.AuthorEntity
import com.demos.booksApi.domain.entities.BookEntity

const val BOOK_A_ISBN = "978-089-230342-0777"

fun testAuthorDtoA(id: Long? = null) = AuthorDto(
    id = id,
    name = "John Doe",
    age = 30,
    description = "Some description",
    image = "author-image.jpeg",
)

fun testAuthorEntityA(id: Long? = null) = AuthorEntity(
    id = id,
    name = "John Doe",
    age = 30,
    description = "Some description",
    image = "author-image.jpeg",
)

fun testAuthorEntityB(id: Long? = null) = AuthorEntity(
    id=id,
    name="Don Joe",
    age=65,
    description = "Some other description",
    image = "some-other-image.jpeg"
)

fun testAuthorSummaryDtoA(id: Long) = AuthorSummaryDto(
    id = id,
    name = "John Doe",
    image = "author-image.jpeg")

fun testAuthorSummaryA(id: Long) = AuthorSummary(
    id = id,
    name = "John Doe",
    image = "author-image.jpeg")

fun testAuthorUpdateRequestDtoA(id: Long? = null) = AuthorUpdateRequestDto(
    id=id,
    name = "John Doe",
    age = 30,
    description = "Some description",
    image =  "author-image.jpeg",
)

fun testAuthorUpdateRequestA(id: Long? = null) = AuthorUpdateRequest(
    id=id,
    name = "John Doe",
    age = 30,
    description = "Some description",
    image =  "author-image.jpeg",
)

fun testBookEntityA(isbn: String, author: AuthorEntity) = BookEntity(
    isbn=isbn,
    title = "Test Book A",
    description = "A test book",
    image = "book-image.jpeg",
    authorEntity = author
)

fun testBookSummaryDtoA(isbn: String, author: AuthorSummaryDto) = BookSummaryDto(
    isbn=isbn,
    title = "Test Book A",
    description = "A test book",
    image = "book-image.jpeg",
    author=author
)

fun testBookSummaryA(isbn: String, author: AuthorSummary) = BookSummary(
    isbn=isbn,
    title = "Test Book A",
    description = "A test book",
    image = "book-image.jpeg",
    author=author
)

fun testBookSummaryB(isbn: String, author: AuthorSummary) = BookSummary(
    isbn=isbn,
    title = "Test Book B",
    description = "Another test book",
    image = "book-image-b.jpeg",
    author=author
)

