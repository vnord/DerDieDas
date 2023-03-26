package com.github.vnord.derdiedas.data.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun

data class NounWithCategories(
    @Embedded val noun: Noun,
    @Relation(
        parentColumn = "nounString",
        entityColumn = "categoryName",
        associateBy = Junction(CategoryNounCrossRef::class),
    )
    val categories: List<Category>,
)
