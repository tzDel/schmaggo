package com.tzDel.schmaggo.customrecipe.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Embeddable

@Embeddable
data class Step(@JsonProperty("text") var text: String)