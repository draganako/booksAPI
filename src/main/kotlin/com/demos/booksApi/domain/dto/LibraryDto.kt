package com.demos.booksApi.domain.dto

import com.demos.booksApi.domain.BookNoLibsDto

data class LibraryDto (
    val id: Long?,
    val description: String?,
    val image: String?,
    val name: String,
    val location: String,
    val books : List<BookNoLibsDto> = listOf()
)