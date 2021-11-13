package com.tzDel.schmaggo.customrecipe.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Embeddable

@Embeddable
data class Ingredient(
    @JsonProperty("name") var name: String,
    @JsonProperty("quantity") var quantity: Int,
    @JsonProperty("unit") var unit: UnitOfMeasure
) {
    enum class UnitOfMeasure(var text: String) { PIECE("pc"), LITRE("l"), GRAM("g") }
}