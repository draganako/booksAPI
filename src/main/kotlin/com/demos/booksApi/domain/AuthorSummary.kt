package com.demos.booksApi.domain

data class AuthorSummary(
    val id: Long,
    val name: String? = null,
    val image: String? = null
)