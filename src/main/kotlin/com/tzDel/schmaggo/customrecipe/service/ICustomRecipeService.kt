package com.tzDel.schmaggo.customrecipe.service

import com.tzDel.schmaggo.customrecipe.model.CustomRecipe

interface ICustomRecipeService {

    fun getAllCustomRecipes(): List<CustomRecipe?>

    fun deleteCustomRecipeId(id: Int)

    fun updateCustomRecipe(id: Int, recipe: CustomRecipe)

    fun addCustomRecipe(recipeToAdd: CustomRecipe)
}