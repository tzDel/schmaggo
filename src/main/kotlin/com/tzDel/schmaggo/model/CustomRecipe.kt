package com.tzDel.schmaggo.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity(name = "custom_recipe")
@Table(name = "custom_recipe")
class CustomRecipe(
    @Id
    @SequenceGenerator(
        name = "recipe_sequence",
        sequenceName = "recipe_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "recipe_sequence"
    )
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("description")
    var description: String?,

    @ElementCollection
    @JsonProperty("steps")
    var steps: List<Step>,

    @ElementCollection
    @JsonProperty("ingredients")
    var ingredients: List<Ingredient>
) {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as CustomRecipe

        if(id == other.id) return true
        if(name != other.name) return false
        if(description != other.description) return false
        if(steps != other.steps) return false
        if(ingredients != other.ingredients) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + steps.hashCode()
        result = 31 * result + ingredients.hashCode()
        return result
    }
}

