package com.tzDel.schmaggo.customrecipe.service

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.tzDel.schmaggo.customrecipe.builder.CustomRecipeBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

internal class CustomRecipeServiceTest {
    lateinit var customRecipeEntityService: CustomRecipeEntityService
    private lateinit var customRecipeService: CustomRecipeService

    @BeforeEach
    fun setup() {
        customRecipeEntityService = mock()
        customRecipeService = CustomRecipeService(customRecipeEntityService)
    }

    @Test
    fun `getAllCustomRecipes should return all custom recipes`() {
        //arrange
        val mockedRecipes = listOf(
            CustomRecipeBuilder().build(),
            CustomRecipeBuilder().build(),
            CustomRecipeBuilder().build()
        )
        whenever(customRecipeEntityService.getAllCustomRecipes()).thenReturn(mockedRecipes)
        //act
        val result = customRecipeService.getAllCustomRecipes()
        //assert
        assertThat(result).isEqualTo(mockedRecipes)
    }

    @Test
    fun `deleteCustomRecipeId should delete custom recipe`() {
        //arrange
        val recipeId = 2
        //act
        customRecipeService.deleteCustomRecipeId(recipeId)
        //assert
        verify(customRecipeEntityService, times(1)).tryDeleteCustomRecipeById(recipeId)
    }

    @Test
    fun `updateCustomRecipe should update custom recipe with all changes`() {
        //arrange
        val recipeId = 2
        val recipeToUpdate = CustomRecipeBuilder(id = recipeId).build()
        val updatedRecipe = CustomRecipeBuilder(id = recipeId, description = "test").build()

        whenever(customRecipeEntityService.tryGetCustomRecipeById(recipeId)).thenReturn(recipeToUpdate)
        //act
        customRecipeService.updateCustomRecipe(recipeId, updatedRecipe)
        //assert
        verify(customRecipeEntityService, times(1)).tryPersistCustomRecipe(updatedRecipe)
    }

    @Test
    fun `addCustomRecipe should persist custom recipe when it is not already existing`() {
        //arrange
        val recipeToAdd = CustomRecipeBuilder().build()
        //act
        customRecipeService.addCustomRecipe(recipeToAdd)
        //assert
        verify(customRecipeEntityService, times(1)).tryPersistCustomRecipe(recipeToAdd)
    }
}