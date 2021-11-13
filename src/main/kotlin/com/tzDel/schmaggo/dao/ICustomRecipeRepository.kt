package com.tzDel.schmaggo.dao

import com.tzDel.schmaggo.model.CustomRecipe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ICustomRecipeRepository: JpaRepository<CustomRecipe, Int> {

    @Query(
        value = "SELECT * " +
                "FROM custom_recipe r " +
                "WHERE r.name LIKE %?1%",
        nativeQuery = true
    )
    fun getCustomRecipesByName(@Param("name") name: String): List<CustomRecipe?>
}