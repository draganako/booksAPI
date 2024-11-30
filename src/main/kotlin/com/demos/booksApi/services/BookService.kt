package com.demos.booksApi.services

import com.demos.booksApi.domain.BookNoLibs
import com.demos.booksApi.domain.BookUpdateRequest
import com.demos.booksApi.domain.entities.BookEntity

interface BookService {

    fun createUpdate(isbn: String, bookRequest: BookNoLibs): Pair<BookEntity, Boolean>

    fun list(authorId: Long?=null): List<BookEntity>

    fun get(isbn: String): BookEntity?

    fun partialUpdate(isbn: String, bookUpdateRequest: BookUpdateRequest): BookEntity

    fun delete(isbn: String)

}