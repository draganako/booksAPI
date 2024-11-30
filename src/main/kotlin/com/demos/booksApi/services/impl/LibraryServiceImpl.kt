package com.demos.booksApi.services.impl

import com.demos.booksApi.domain.LibraryUpdateRequest
import com.demos.booksApi.domain.entities.BookEntity
import com.demos.booksApi.domain.entities.LibraryEntity
import com.demos.booksApi.exceptions.BookAlreadyExistsException
import com.demos.booksApi.repositories.BookRepository
import com.demos.booksApi.repositories.LibraryRepository
import com.demos.booksApi.services.LibraryService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LibraryServiceImpl(
    private val libraryRepository: LibraryRepository,
    private val bookRepository: BookRepository
) : LibraryService {

    override fun create(libraryEntity: LibraryEntity): LibraryEntity {
        require(null == libraryEntity.id)
        return libraryRepository.save(libraryEntity)
    }

    override fun list(): List<LibraryEntity> {
        return libraryRepository.findAll()
    }

    override fun get(id: Long): LibraryEntity? {
        return libraryRepository.findByIdOrNull(id)
    }

    @Transactional
    override fun fullUpdate(id: Long, libraryEntity: LibraryEntity): LibraryEntity {
        check(libraryRepository.existsById(id))
        val normalisedLibrary = libraryEntity.copy(id=id)
        return libraryRepository.save(normalisedLibrary)
    }

    @Transactional
    override fun partialUpdate(id: Long, libraryUpdateRequest: LibraryUpdateRequest): LibraryEntity {
        val existingLibrary = libraryRepository.findByIdOrNull(id)
        checkNotNull(existingLibrary)

        val updatedLibrary = existingLibrary.copy(
            name = libraryUpdateRequest.name ?: existingLibrary.name,
            image = libraryUpdateRequest.image ?: existingLibrary.image,
            description = libraryUpdateRequest.description ?: existingLibrary.description,
            location = libraryUpdateRequest.location ?: existingLibrary.location
        )

        return libraryRepository.save(updatedLibrary)
    }

    override fun delete(id: Long) {
        libraryRepository.deleteById(id)
    }

    override fun addBookToLibrary(bookIsbn: String, libraryId: Long): LibraryEntity {
        val books: MutableList<BookEntity>

        val bookEntity = bookRepository.findByIdOrNull(bookIsbn)
        checkNotNull(bookEntity)

        val libraryEntity = libraryRepository.findByIdOrNull(libraryId)
        checkNotNull(libraryEntity)

        books = libraryEntity.books.toMutableList()
        for (book in books) {
            if (book.isbn == bookIsbn)
                throw BookAlreadyExistsException()
        }
        books.add(bookEntity)

        libraryEntity.books = books
        return libraryRepository.save(libraryEntity)
    }
}