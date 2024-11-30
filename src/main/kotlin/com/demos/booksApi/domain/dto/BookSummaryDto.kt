package com.demos.booksApi.domain.dto

import com.demos.booksApi.domain.entities.LibraryEntity

data class BookSummaryDto(
    val isbn: String,
    val title: String,
    val description: String,
    val image: String,
    val author: AuthorSummaryDto,
    val libraries : List<LibraryEntity> = listOf()
)