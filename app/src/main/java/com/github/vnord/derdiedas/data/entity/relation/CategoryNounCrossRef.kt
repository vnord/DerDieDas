package com.github.vnord.derdiedas.data.entity.relation

import androidx.room.Entity

@Entity(primaryKeys = ["categoryName", "nounString"])
data class CategoryNounCrossRef(
    val categoryName: String,
    val nounString: String,
)
