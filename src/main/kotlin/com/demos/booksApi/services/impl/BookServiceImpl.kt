package com.demos.booksApi.services.impl

import com.demos.booksApi.domain.BookNoLibs
import com.demos.booksApi.toBookEntity
import com.demos.booksApi.domain.BookUpdateRequest
import com.demos.booksApi.domain.entities.BookEntity
import com.demos.booksApi.repositories.AuthorRepository
import com.demos.booksApi.repositories.BookRepository
import com.demos.booksApi.services.BookService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository
) : BookService {

    @Transactional
    override fun createUpdate(isbn: String, bookRequest: BookNoLibs): Pair<BookEntity, Boolean> {
        val normalisedBook = bookRequest.copy(isbn = isbn)
        val isExists = bookRepository.existsById(isbn)

        val author = authorRepository.findByIdOrNull(normalisedBook.authorSummary.id)
        checkNotNull(author)

        val existingBook = bookRepository.findByIdOrNull(isbn)
        val updatedBook : BookEntity = existingBook?.copy(
            title = normalisedBook.title,
            description = normalisedBook.description ?: existingBook.description,
            image = normalisedBook.image ?: existingBook.image,
            authorEntity = author
        ) ?: normalisedBook.toBookEntity(author)

        val savedBook = bookRepository.save(updatedBook)
        return Pair(savedBook, !isExists)
    }

    override fun list(authorId: Long?): List<BookEntity> {
        return authorId?.let {
            bookRepository.findByAuthorEntityId(it)
        } ?: bookRepository.findAll()
    }

    override fun get(isbn: String): BookEntity? {
        return bookRepository.findByIdOrNull(isbn)
    }

    override fun partialUpdate(isbn: String, bookUpdateRequest: BookUpdateRequest): BookEntity {
        val existingBook = bookRepository.findByIdOrNull(isbn)
        checkNotNull(existingBook)

        val updatedBook = existingBook.copy(
            title = bookUpdateRequest.title ?: existingBook.title,
            description = bookUpdateRequest.description ?: existingBook.description,
            image = bookUpdateRequest.image ?: existingBook.image
        )

        return bookRepository.save(updatedBook)
    }

    override fun delete(isbn: String) {
        bookRepository.deleteById(isbn)
    }

}