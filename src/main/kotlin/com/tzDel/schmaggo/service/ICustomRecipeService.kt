package com.tzDel.schmaggo.service

import com.tzDel.schmaggo.exception.RecipeAlreadyExistingException
import com.tzDel.schmaggo.model.CustomRecipe

interface ICustomRecipeService {

    fun getAllCustomRecipes(): List<CustomRecipe?>

    fun deleteCustomRecipeId(id: Int)

    fun updateCustomRecipe(id: Int, recipe: CustomRecipe)

    fun addCustomRecipe(recipeToAdd: CustomRecipe)
}