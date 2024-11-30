package com.demos.booksApi.domain

import com.demos.booksApi.domain.dto.AuthorSummaryDto

data class BookNoLibsDto(
    val isbn: String,
    val title: String,
    val description: String?,
    val image: String?,
    val authorSummaryDto: AuthorSummaryDto
)