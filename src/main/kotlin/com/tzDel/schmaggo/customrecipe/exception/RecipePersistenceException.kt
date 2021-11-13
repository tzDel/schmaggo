package com.tzDel.schmaggo.customrecipe.exception

import java.lang.RuntimeException

class RecipePersistenceException(
    override val cause: Throwable
): RuntimeException(REASON, cause) {
    companion object {
        const val REASON = "Custom recipe could not be persisted"
    }
}

