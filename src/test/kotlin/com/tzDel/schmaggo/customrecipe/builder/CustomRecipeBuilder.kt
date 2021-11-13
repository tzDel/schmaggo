package com.tzDel.schmaggo.customrecipe.builder

import com.tzDel.schmaggo.customrecipe.model.CustomRecipe
import com.tzDel.schmaggo.customrecipe.model.Ingredient
import com.tzDel.schmaggo.customrecipe.model.Step

data class CustomRecipeBuilder(
    var id: Int = Math.random().toInt(),
    var name: String = "",
    var description: String = "",
    var temporalLength: Int = Math.random().toInt(),
    var steps: List<Step> = listOf(
        Step("")
    ),
    var ingredients: List<Ingredient> = listOf(
        Ingredient(
            "",
            Math.random().toInt(),
            Ingredient.UnitOfMeasure.GRAM
        )
    )
) {
  fun build() = CustomRecipe(
      id = id,
      name = name,
      description = description,
      temporalLength = temporalLength,
      steps = steps,
      ingredients = ingredients
  )
}