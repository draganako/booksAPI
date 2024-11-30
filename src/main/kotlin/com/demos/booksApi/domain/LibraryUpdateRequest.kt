package com.demos.booksApi.domain

data class LibraryUpdateRequest (
    val id: Long? = null,
    val description: String? = null,
    val image: String? = null,
    val name: String? = null,
    val location: String? = null,
    //val books : List<BookSummary>? = null
)