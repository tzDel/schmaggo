package com.tzDel.schmaggo.customrecipe.service

import com.nhaarman.mockitokotlin2.*
import com.tzDel.schmaggo.customrecipe.builder.CustomRecipeBuilder
import com.tzDel.schmaggo.customrecipe.dao.ICustomRecipeRepository
import com.tzDel.schmaggo.customrecipe.exception.RecipeAlreadyExistingException
import com.tzDel.schmaggo.customrecipe.exception.RecipeDeletionException
import com.tzDel.schmaggo.customrecipe.exception.RecipeNotFoundException
import com.tzDel.schmaggo.customrecipe.exception.RecipePersistenceException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.times
import java.lang.IllegalArgumentException
import javax.persistence.EntityNotFoundException

internal class CustomRecipeEntityServiceTest {
    lateinit var customRecipeRepository: ICustomRecipeRepository
    private lateinit var customServiceEntityService: CustomRecipeEntityService

    @BeforeEach
    fun setup() {
        customRecipeRepository = mock()
        customServiceEntityService = CustomRecipeEntityService(customRecipeRepository)
    }

    @Test
    fun `tryGetCustomRecipeById should return custom recipe`() {
        //arrange
        val recipeId = 2
        val recipe = CustomRecipeBuilder(id = recipeId).build()
        whenever(customRecipeRepository.getById(recipeId))
            .thenReturn(recipe)
        //act
        val result = customServiceEntityService.tryGetCustomRecipeById(recipeId)
        //assert
        assertThat(result).isEqualTo(recipe)
    }

    @Test
    fun `tryGetCustomRecipeById should throw RecipeNotFoundException on EntityNotFoundException`() {
        //arrange
        whenever(customRecipeRepository.getById(any()))
            .thenThrow(EntityNotFoundException())
        //act & assert
        assertThrows(RecipeNotFoundException::class.java) {
            customServiceEntityService.tryGetCustomRecipeById(any())
        }
    }

    @Test
    fun `tryGetCustomRecipesByName should return all recipes whose name contain the search term`() {
        //arrange
        val searchTerm = "curry"
        val mockedRecipes = listOf(
            CustomRecipeBuilder(name = "yellow curry").build(),
            CustomRecipeBuilder(name = "red curry with rice").build(),
            CustomRecipeBuilder(name = "curry with green color").build()
        )
        whenever(customRecipeRepository.getCustomRecipesByName(searchTerm))
            .thenReturn(mockedRecipes)
        //act
        val result = customServiceEntityService.tryGetCustomRecipesByName(searchTerm)
        //assert
        assertThat(result).isEqualTo(mockedRecipes)
    }

    @Test
    fun `tryGetCustomRecipesByName should throw RecipeNotFoundException on IllegalArgumentException`() {
        //arrange
        whenever(customRecipeRepository.getCustomRecipesByName(anyString()))
            .thenThrow(IllegalArgumentException())
        //act & assert
        assertThrows(RecipeNotFoundException::class.java) {
            customServiceEntityService.tryGetCustomRecipesByName(anyString())
        }
    }

    @Test
    fun `tryPersistCustomRecipe should persist custom recipe`() {
        //arrange
        val recipeToPersist = CustomRecipeBuilder().build()
        whenever(customRecipeRepository.save(recipeToPersist)).thenReturn(recipeToPersist)
        //act
        customServiceEntityService.tryPersistCustomRecipe(recipeToPersist)
        //assert
        verify(customRecipeRepository, times(1)).save(recipeToPersist)
    }

    @Test
    fun `tryPersistCustomRecipe should throw RecipePersistenceException on IllegalArgumentException`() {
        //arrange
        val recipeToPersist = CustomRecipeBuilder().build()
        whenever(customRecipeRepository.save(recipeToPersist))
            .thenThrow(IllegalArgumentException())
        //act & assert
        assertThrows(RecipePersistenceException::class.java) {
            customServiceEntityService.tryPersistCustomRecipe(recipeToPersist)
        }
    }

    @Test
    fun `tryDeleteCustomRecipeById should delete custom recipe`() {
        //arrange
        val recipeId = 2
        doNothing().whenever(customRecipeRepository).deleteById(recipeId)
        //act
        customServiceEntityService.tryDeleteCustomRecipeById(recipeId)
        //assert
        verify(customRecipeRepository, times(1)).deleteById(recipeId)
    }

    @Test
    fun `tryDeleteCustomRecipeById should throw RecipeDeletionException on IllegalArgumentException`() {
        //arrange
        val recipeId = 2
        whenever(customRecipeRepository.deleteById(recipeId))
            .thenThrow(IllegalArgumentException())
        //act & assert
        assertThrows(RecipeDeletionException::class.java) {
            customServiceEntityService.tryDeleteCustomRecipeById(recipeId)
        }
    }

    @Test
    fun `assertRecipeToAddIsUnique should throw RecipeAlreadyExistingException when custom recipe is already existing`() {
        //arrange
        val recipe = CustomRecipeBuilder(name = "curry").build()
        whenever(customServiceEntityService.tryGetCustomRecipesByName(recipe.name))
            .thenReturn(listOf(recipe))
        //act & assert
        assertThrows(RecipeAlreadyExistingException::class.java) {
            customServiceEntityService.assertRecipeToAddIsUnique(recipe)
        }
    }
}