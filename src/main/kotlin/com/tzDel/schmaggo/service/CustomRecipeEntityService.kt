package com.tzDel.schmaggo.service

import com.tzDel.schmaggo.dao.ICustomRecipeRepository
import com.tzDel.schmaggo.exception.RecipeAlreadyExistingException
import com.tzDel.schmaggo.exception.RecipeDeletionException
import com.tzDel.schmaggo.exception.RecipeNotFoundException
import com.tzDel.schmaggo.exception.RecipePersistenceException
import com.tzDel.schmaggo.model.CustomRecipe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import javax.persistence.EntityNotFoundException

@Service
class CustomRecipeEntityService(
    @Autowired private val customRecipeRepository: ICustomRecipeRepository
) {
    fun tryGetCustomRecipeById(id: Int): CustomRecipe = try {
        customRecipeRepository.getById(id)
    } catch (ex: EntityNotFoundException) {
        throw RecipeNotFoundException(ex)
    }
    
    fun tryGetCustomRecipesByName(recipeName: String): List<CustomRecipe?> = try {
        customRecipeRepository.getCustomRecipesByName(recipeName)
    } catch (ex: IllegalArgumentException) {
        throw RecipeNotFoundException(ex)
    }
    
    fun tryPersistCustomRecipe(recipeToPersist: CustomRecipe) = try {
        customRecipeRepository.save(recipeToPersist)
    } catch (ex: IllegalArgumentException) {
        throw RecipePersistenceException(ex)
    }
    
    fun tryDeleteCustomRecipeById(id: Int) = try {
        customRecipeRepository.deleteById(id)
    } catch (ex: IllegalArgumentException) {
        throw RecipeDeletionException(ex)
    }

    fun assertRecipeToAddIsUnique(recipeToAdd: CustomRecipe) {
        if (isAlreadyExisting(recipeToAdd)) {
            throw RecipeAlreadyExistingException()
        }
    }
    
    fun getAllCustomRecipes(): List<CustomRecipe?> = customRecipeRepository.findAll()

    private fun isAlreadyExisting(recipe: CustomRecipe) = tryGetCustomRecipesByName(recipe.name).any {recipe == it}
}


