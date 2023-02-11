package com.github.vnord.derdiedas.feature_nouns.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Locale

@Entity(tableName = "noun")
data class Noun(
    @PrimaryKey
    @ColumnInfo(name = "noun")
    val noun: String,

    @ColumnInfo(name = "gender")
    val gender: Gender,
)

enum class Gender(val str: String) {
    DER("der"), DIE("die"), DAS("das");

    companion object {
        fun parse(str: String): Gender {
            return when (str.lowercase(Locale.ROOT)) {
                "der" -> DER
                "die" -> DIE
                "das" -> DAS
                else -> throw Exception("no such gender: $str")
            }
        }
    }
}
