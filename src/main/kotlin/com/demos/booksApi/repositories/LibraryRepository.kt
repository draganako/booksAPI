package com.demos.booksApi.repositories

import com.demos.booksApi.domain.entities.LibraryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LibraryRepository: JpaRepository<LibraryEntity, Long>