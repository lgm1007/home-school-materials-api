package org.freewheelin.homeschoolmaterials.api.advice

import org.freewheelin.homeschoolmaterials.api.error.ErrorBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.webjars.NotFoundException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorBody> {
        return ResponseEntity(ErrorBody(e.message ?: "", 404), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorBody> {
        return ResponseEntity(ErrorBody(e.message ?: "", 400), HttpStatus.BAD_REQUEST)
    }
}