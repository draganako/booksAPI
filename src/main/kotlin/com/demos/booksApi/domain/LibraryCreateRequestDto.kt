package com.demos.booksApi.domain

data class LibraryCreateRequestDto (
    val id: Long?,
    val description: String?,
    val image: String?,
    val name: String,
    val location: String,
)