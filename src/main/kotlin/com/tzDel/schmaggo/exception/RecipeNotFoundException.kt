package com.tzDel.schmaggo.exception

import java.lang.RuntimeException

class RecipeNotFoundException(
    override val cause: Throwable
): RuntimeException(REASON, cause) {
    companion object {
        const val REASON = "Custom recipe was not found with given ID"
    }
}

