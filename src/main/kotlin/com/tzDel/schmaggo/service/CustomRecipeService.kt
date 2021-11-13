package com.tzDel.schmaggo.service

import com.tzDel.schmaggo.model.CustomRecipe
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomRecipeService(
    @Autowired private val customRecipeEntityService: CustomRecipeEntityService
): ICustomRecipeService {
    private val logger = LoggerFactory.getLogger(CustomRecipeService::class.java)

    override fun getAllCustomRecipes(): List<CustomRecipe?> = customRecipeEntityService.getAllCustomRecipes()
    override fun deleteCustomRecipeId(id: Int) = customRecipeEntityService.tryDeleteCustomRecipeById(id)

    override fun updateCustomRecipe(id: Int, recipe: CustomRecipe) {
        logger.info("Start updating recipe ${recipe.name} with ID: $id")

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
        logger.info("Start adding recipe ${recipeToAdd.name}")
        customRecipeEntityService.assertRecipeToAddIsUnique(recipeToAdd)
        customRecipeEntityService.tryPersistCustomRecipe(recipeToAdd)
    }
}
