package com.github.vnord.derdiedas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noun")
data class Noun(
    @PrimaryKey
    @ColumnInfo(name = "noun")
    val noun: String,

    @ColumnInfo(name = "gender")
    val gender: Gender,
)

enum class Gender(val str: String) {
    MASCULINE("der"), FEMININE("die"), NEUTER("das")
}
