package com.demos.booksApi.services

import com.demos.booksApi.domain.LibraryUpdateRequest
import com.demos.booksApi.domain.entities.LibraryEntity

interface LibraryService {
    fun create(libraryEntity: LibraryEntity): LibraryEntity

    fun list(): List<LibraryEntity>

    fun get(id: Long): LibraryEntity?

    fun fullUpdate(id: Long, libraryEntity: LibraryEntity): LibraryEntity

    fun partialUpdate(id: Long, libraryUpdateRequest: LibraryUpdateRequest): LibraryEntity

    fun delete(id: Long)

    fun addBookToLibrary(bookIsbn: String, libraryId: Long): LibraryEntity
}