package com.github.vnord.derdiedas.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey
    val categoryName: String,
    val color: Int,
)
