package com.demos.booksApi.domain.dto

import com.demos.booksApi.domain.entities.LibraryEntity

data class BookDto(
    val isbn: String,
    val title: String,
    val description: String,
    val image: String,
    val author: AuthorDto,
    val libraries : List<LibraryEntity> = listOf()
)