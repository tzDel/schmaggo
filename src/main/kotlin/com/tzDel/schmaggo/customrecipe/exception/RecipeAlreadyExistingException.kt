package com.tzDel.schmaggo.customrecipe.exception

import java.lang.RuntimeException

class RecipeAlreadyExistingException: RuntimeException(REASON) {
    companion object {
        const val REASON = "Custom recipe is already existing"
    }
}

