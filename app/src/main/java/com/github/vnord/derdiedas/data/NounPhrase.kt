package com.github.vnord.derdiedas.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noun_phrase")
data class NounPhrase(
    @PrimaryKey
    @ColumnInfo(name = "noun")
    val noun: String,
    @ColumnInfo(name = "gender")
    val gender: Gender
)

enum class Gender(val str: String) { DER("der"), DIE("die"), DAS("das") }
