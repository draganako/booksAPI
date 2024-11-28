package com.demos.booksApi.controllers

import com.demos.booksApi.domain.dto.AuthorDto
import com.demos.booksApi.domain.dto.AuthorUpdateRequestDto
import com.demos.booksApi.services.AuthorService
import com.demos.booksApi.toAuthorDto
import com.demos.booksApi.toAuthorEntity
import com.demos.booksApi.toAuthorUpdateRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name="Authors")
@RestController
@RequestMapping(path = ["/v1/authors"])
class AuthorsController(private val authorService: AuthorService) {

    @PostMapping
    fun createAuthor(@RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        return try {
            val createdAuthor = authorService.create(
                authorDto.toAuthorEntity()
            ).toAuthorDto()
            ResponseEntity(createdAuthor, HttpStatus.CREATED)

        } catch(ex: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun readManyAuthor(): List<AuthorDto> {
        return authorService.list().map { it.toAuthorDto() }
    }

    @GetMapping(path = ["/{id}"])
    fun readOneAuthor(@PathVariable("id") id: Long): ResponseEntity<AuthorDto> {
        val foundAuthor = authorService.get(id)?.toAuthorDto()
        return foundAuthor?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping(path=["/{id}"])
    fun fullUpdateAuthor(@PathVariable("id") id: Long, @RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        return try {
            val updatedAuthor = authorService.fullUpdate(id, authorDto.toAuthorEntity())
            ResponseEntity(updatedAuthor.toAuthorDto(), HttpStatus.OK)

        } catch(ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping(path=["/{id}"])
    fun partialUpdateAuthor(@PathVariable("id") id: Long, @RequestBody authorUpdateRequest: AuthorUpdateRequestDto): ResponseEntity<AuthorDto> {
        return try {
            val updatedAuthor = authorService.partialUpdate(id, authorUpdateRequest.toAuthorUpdateRequest())
            ResponseEntity(updatedAuthor.toAuthorDto(), HttpStatus.OK)
        } catch (ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(path=["/{id}"])
    fun deleteAuthor(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        authorService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}