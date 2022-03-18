package com.learning.springbootkotlinh2.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler
    fun handleException(ex: ProductNotFound) : ResponseEntity<CustomResponse>{
        val customResponse= CustomResponse(HttpStatus.NOT_FOUND.value(), ex.message, System.currentTimeMillis())
        return ResponseEntity(customResponse,HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleGenericException(ex : Exception) : ResponseEntity<CustomResponse> {
        val customResponse = CustomResponse(HttpStatus.BAD_REQUEST.value(),
            ex.message,
            System.currentTimeMillis())

        return ResponseEntity(customResponse, HttpStatus.BAD_REQUEST)
    }
}