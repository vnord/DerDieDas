package com.github.vnord.derdiedas.data.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun

data class CategoryWithNouns(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "nounString",
        associateBy = Junction(CategoryNounCrossRef::class),
    )
    val nouns: List<Noun>,
)
