package com.demos.booksApi.domain.dto

data class LibraryUpdateRequestDto (
    val id: Long?,
    val description: String?,
    val image: String?,
    val name: String?,
    val location: String?,
)