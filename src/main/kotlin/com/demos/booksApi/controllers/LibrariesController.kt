package com.demos.booksApi.controllers

import com.demos.booksApi.*
import com.demos.booksApi.domain.LibraryCreateRequestDto
import com.demos.booksApi.domain.dto.LibraryDto
import com.demos.booksApi.domain.dto.LibraryUpdateRequestDto
import com.demos.booksApi.exceptions.BookAlreadyExistsException
import com.demos.booksApi.services.LibraryService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name="Libraries")
@RestController
@RequestMapping("/v1/libraries")
class LibrariesController(private val libraryService: LibraryService) {

    @PostMapping
    fun createLibrary(@RequestBody libraryDto: LibraryCreateRequestDto): ResponseEntity<LibraryDto> {
        return try {
            val createdLibrary = libraryService.create(
                libraryDto.toLibraryEntity()
            ).toLibraryDto()

            ResponseEntity(createdLibrary, HttpStatus.CREATED)

        } catch(ex: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun readManyLibrary(): List<LibraryDto> {
        return libraryService.list().map { it.toLibraryDto() }
    }

    @GetMapping(path = ["/{id}"])
    fun readOneLibrary(@PathVariable("id") id: Long): ResponseEntity<LibraryDto> {
        val foundLibrary = libraryService.get(id)?.toLibraryDto()
        return foundLibrary?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping(path=["/{id}"])
    fun fullUpdateLibrary(@PathVariable("id") id: Long, @RequestBody libraryDto: LibraryCreateRequestDto): ResponseEntity<LibraryDto> {
        return try {
            val updatedLibrary = libraryService.fullUpdate(id, libraryDto.toLibraryEntity())
            ResponseEntity(updatedLibrary.toLibraryDto(), HttpStatus.OK)

        } catch(ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping(path=["/{id}"])
    fun partialUpdateLibrary(@PathVariable("id") id: Long, @RequestBody libraryUpdateRequest: LibraryUpdateRequestDto): ResponseEntity<LibraryDto> {
        return try {
            val updatedLibrary = libraryService.partialUpdate(id, libraryUpdateRequest.toLibraryUpdateRequest())
            ResponseEntity(updatedLibrary.toLibraryDto(), HttpStatus.OK)
        } catch (ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping(path=["/{id}/books/{isbn}"])//cascade type ALL ensures books are saved in db after being added to lib!
    fun updateBooksLibrary(@PathVariable("id") id: Long, @PathVariable("isbn") isbn: String): ResponseEntity<LibraryDto> {
        return try {
            val updatedLibrary = libraryService.addBookToLibrary(isbn, id)
            ResponseEntity(updatedLibrary.toLibraryDto(), HttpStatus.OK)
        }
        catch (ex: BookAlreadyExistsException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(path=["/{id}"])
    fun deleteLibrary(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        libraryService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}