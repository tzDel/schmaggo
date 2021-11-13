package com.tzDel.schmaggo.customrecipe.service

import com.tzDel.schmaggo.customrecipe.dao.ICustomRecipeRepository
import com.tzDel.schmaggo.customrecipe.exception.RecipeAlreadyExistingException
import com.tzDel.schmaggo.customrecipe.exception.RecipeDeletionException
import com.tzDel.schmaggo.customrecipe.exception.RecipeNotFoundException
import com.tzDel.schmaggo.customrecipe.exception.RecipePersistenceException
import com.tzDel.schmaggo.customrecipe.model.CustomRecipe
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import javax.persistence.EntityNotFoundException

@Service
class CustomRecipeEntityService(
    @Autowired private val customRecipeRepository: ICustomRecipeRepository
) {
    private val logger = LoggerFactory.getLogger(CustomRecipeService::class.java)

    fun tryGetCustomRecipeById(id: Int): CustomRecipe = try {
        logger.info("Fetching custom recipe by ID $id ...")
        customRecipeRepository.getById(id)
    } catch (ex: EntityNotFoundException) {
        logger.info("Fetching custom recipe by id $id failed")
        throw RecipeNotFoundException(ex)
    }
    
    fun tryGetCustomRecipesByName(recipeName: String): List<CustomRecipe?> = try {
        logger.info("Fetching custom recipe by name $recipeName ...")
        customRecipeRepository.getCustomRecipesByName(recipeName)
    } catch (ex: IllegalArgumentException) {
        logger.info("Fetching custom recipe by name $recipeName failed")
        throw RecipeNotFoundException(ex)
    }
    
    fun tryPersistCustomRecipe(recipeToPersist: CustomRecipe) = try {
        logger.info("Start persisting recipe ${recipeToPersist.name} with id ${recipeToPersist.id} failed")
        customRecipeRepository.save(recipeToPersist)
    } catch (ex: IllegalArgumentException) {
        logger.info("Persisting custom recipe ${recipeToPersist.name} with id ${recipeToPersist.id} failed")
        throw RecipePersistenceException(ex)
    }
    
    fun tryDeleteCustomRecipeById(id: Int) = try {
        logger.info("Try deleting recipe with id $id")
        customRecipeRepository.deleteById(id)
    } catch (ex: IllegalArgumentException) {
        logger.info("Deleting recipe with id $id failed")
        throw RecipeDeletionException(ex)
    }

    fun assertRecipeToAddIsUnique(recipeToAdd: CustomRecipe) {
        if (isAlreadyExisting(recipeToAdd)) {
            logger.info("Recipe ${recipeToAdd.name} already exists")
            throw RecipeAlreadyExistingException()
        }
    }
    
    fun getAllCustomRecipes(): List<CustomRecipe?> = customRecipeRepository.findAll()

    private fun isAlreadyExisting(recipe: CustomRecipe) = tryGetCustomRecipesByName(recipe.name).any {recipe == it}
}


