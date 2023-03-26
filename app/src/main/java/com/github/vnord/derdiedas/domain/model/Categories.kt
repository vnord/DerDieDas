package com.github.vnord.derdiedas.domain.model

import androidx.compose.ui.graphics.toArgb
import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.ui.theme.RedOrange
import com.github.vnord.derdiedas.ui.theme.RedPink

object Categories {
    private val categoryColors = listOf(RedOrange, RedPink)
    val MyNouns = Category("My Nouns", categoryColors.first().toArgb())
    val Top100 = Category("Top 100", categoryColors.last().toArgb())

    fun fromString(name: String): Category {
        return when (name) {
            MyNouns.categoryName -> MyNouns
            Top100.categoryName -> Top100
            else -> throw IllegalArgumentException("Unknown category name: $name")
        }
    }
}
