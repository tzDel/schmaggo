package com.tzDel.schmaggo.service

import com.tzDel.schmaggo.dao.ICustomRecipeRepository
import com.tzDel.schmaggo.exception.RecipeAlreadyExistingException
import com.tzDel.schmaggo.model.CustomRecipe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomRecipeService(
    @Autowired private val customRecipeRepository: ICustomRecipeRepository
): ICustomRecipeService {

    override fun getAllCustomRecipes(): List<CustomRecipe?> = customRecipeRepository.findAll()

    override fun deleteCustomRecipeId(id: Int) = customRecipeRepository.deleteById(id)

    override fun updateCustomRecipe(id: Int, recipe: CustomRecipe) {
        val updatedRecipe = customRecipeRepository.getById(id).apply {
            name = recipe.name
            description = recipe.description
            temporalLength = recipe.temporalLength
            ingredients = recipe.ingredients
            steps = recipe.steps
        }
        customRecipeRepository.save(updatedRecipe)
    }

    override fun addCustomRecipe(recipeToAdd: CustomRecipe) {
        if(isAlreadyExisting(recipeToAdd)) {
            throw RecipeAlreadyExistingException()
        }
        customRecipeRepository.save(recipeToAdd)
    }

    private fun isAlreadyExisting(recipe: CustomRecipe) =
        customRecipeRepository.getCustomRecipesByName(recipe.name).any {
            recipe == it
        }
}
