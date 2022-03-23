package com.tzDel.schmaggo.customrecipe.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class RecipeExceptionHandler {

    @ExceptionHandler(RecipeAlreadyExistingException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = RecipeAlreadyExistingException.REASON)
    fun handleRecipeAlreadyExistingException(exception: RecipeAlreadyExistingException){ }

    @ExceptionHandler(RecipeNotFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = RecipeNotFoundException.REASON)
    fun handleRecipeNotFoundException(exception: RecipeNotFoundException){ }

    @ExceptionHandler(RecipePersistenceException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = RecipePersistenceException.REASON)
    fun handleRecipePersistenceException(exception: RecipePersistenceException){ }

    @ExceptionHandler(RecipeDeletionException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = RecipeDeletionException.REASON)
    fun handleRecipeDeletionException(exception: RecipeDeletionException){ }
}