package com.demos.booksApi.domain

data class BookNoLibs(
    val isbn: String,
    val title: String,
    val description: String?,
    val image: String?,
    val authorSummary: AuthorSummary
)