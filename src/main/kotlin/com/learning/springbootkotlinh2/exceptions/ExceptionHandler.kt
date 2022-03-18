//package com.learning.springbootkotlinh2.exceptions
//
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.ExceptionHandler
//import org.springframework.web.bind.annotation.RestControllerAdvice
//import kotlin.Exception
//
//
//@RestControllerAdvice
//class ExceptionHandler {
//
//    @ExceptionHandler(Exception::class)
//    fun exceptionHandler(exception: Exception): ResponseEntity<ApiError>{
//        val error= ApiError(exception.message)
//        return ResponseEntity(error, error.status)
//    }
//}