package com.tzDel.schmaggo.controller

import com.tzDel.schmaggo.model.CustomRecipe
import com.tzDel.schmaggo.service.CustomRecipeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/custom_recipe")
class CustomRecipeController(@Autowired private val customRecipeService: CustomRecipeService) {

    @GetMapping("/getAll")
    fun getAllRecipes() = customRecipeService.getAllCustomRecipes()

    @DeleteMapping("/delete")
    fun deleteRecipeById(@RequestParam id: Int) = customRecipeService.deleteCustomRecipeId(id)

    @PutMapping("/update")
    fun updateRecipe(
        @RequestParam id: Int,
        @RequestBody recipe: CustomRecipe
    ) = customRecipeService.updateCustomRecipe(id, recipe)

    @PostMapping("/add")
    fun addRecipe(@RequestBody recipe: CustomRecipe) = customRecipeService.addCustomRecipe(recipe)
}