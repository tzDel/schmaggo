package com.tzDel.schmaggo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class RecipeExceptionHandler {

    @ExceptionHandler(RecipeAlreadyExistingException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = RecipeAlreadyExistingException.REASON)
    fun handleApiRequestException(exception: RecipeAlreadyExistingException){ }
}