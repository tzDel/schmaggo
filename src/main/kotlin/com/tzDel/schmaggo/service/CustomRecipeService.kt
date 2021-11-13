package com.tzDel.schmaggo.service

import com.tzDel.schmaggo.model.CustomRecipe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomRecipeService(
    @Autowired private val customRecipeEntityService: CustomRecipeEntityService
): ICustomRecipeService {

    override fun getAllCustomRecipes(): List<CustomRecipe?> = customRecipeEntityService.getAllCustomRecipes()

    override fun deleteCustomRecipeId(id: Int) = customRecipeEntityService.tryDeleteCustomRecipeById(id)

    override fun updateCustomRecipe(id: Int, recipe: CustomRecipe) {
        val updatedRecipe = customRecipeEntityService
            .tryGetCustomRecipeById(id)
            .apply {
                name = recipe.name
                description = recipe.description
                temporalLength = recipe.temporalLength
                ingredients = recipe.ingredients
                steps = recipe.steps
            }
        customRecipeEntityService.tryPersistCustomRecipe(updatedRecipe)
    }

    override fun addCustomRecipe(recipeToAdd: CustomRecipe) {
        customRecipeEntityService.assertRecipeToAddIsUnique(recipeToAdd)
        customRecipeEntityService.tryPersistCustomRecipe(recipeToAdd)
    }
}
