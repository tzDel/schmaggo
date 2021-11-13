package com.tzDel.schmaggo.service

import com.tzDel.schmaggo.dao.ICustomRecipeRepository
import com.tzDel.schmaggo.model.CustomRecipe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomRecipeService(@Autowired private val customRecipeRepository: ICustomRecipeRepository) {

    fun getAllCustomRecipes(): List<CustomRecipe?> = customRecipeRepository.findAll()

    fun deleteCustomRecipeId(id: Int) = customRecipeRepository.deleteById(id)

    fun updateCustomRecipe(id: Int, recipe: CustomRecipe): UpdateCustomRecipeResponse {
        val updatedRecipe = customRecipeRepository.getById(id).apply {
            name = recipe.name
            description = recipe.description
            ingredients = recipe.ingredients
            steps = recipe.steps
        }
        customRecipeRepository.save(updatedRecipe)
        return UpdateCustomRecipeResponse.SUCCESSFULLY_UPDATED
    }

    fun addCustomRecipe(recipeToAdd: CustomRecipe): AddCustomRecipeResponse {
        if(isAlreadyExisting(recipeToAdd)) {
            return AddCustomRecipeResponse.RECIPE_ALREADY_EXISTS
        }
        customRecipeRepository.save(recipeToAdd)
        return AddCustomRecipeResponse.SUCCESSFULLY_ADDED
    }

    private fun isAlreadyExisting(recipe: CustomRecipe) =
        customRecipeRepository.getCustomRecipesByName(recipe.name).any {
            recipe == it
        }
}

enum class AddCustomRecipeResponse {
    RECIPE_ALREADY_EXISTS,
    SUCCESSFULLY_ADDED
}

enum class UpdateCustomRecipeResponse {
    SUCCESSFULLY_UPDATED,
    ERROR
}
