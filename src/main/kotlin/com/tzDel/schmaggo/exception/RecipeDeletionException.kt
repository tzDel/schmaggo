package com.tzDel.schmaggo.exception

import java.lang.RuntimeException

class RecipeDeletionException(
    override val cause: Throwable
): RuntimeException(REASON, cause) {
    companion object {
        const val REASON = "Custom recipe could not be deleted"
    }
}

