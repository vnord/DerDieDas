package com.github.vnord.derdiedas.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article(
    @PrimaryKey
    @ColumnInfo(name = "article")
    val article: String,
    @ColumnInfo(name = "gender")
    val gender: Gender
)

enum class Gender { DER, DIE, DAS }