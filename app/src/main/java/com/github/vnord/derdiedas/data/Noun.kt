package com.github.vnord.derdiedas.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "noun")
data class Noun(
    @PrimaryKey
    @ColumnInfo(name = "noun")
    val noun: String,

    @ColumnInfo(name = "gender")
    val gender: Gender,

    @ColumnInfo(name = "last_review")
    val lastReview: Long? = null,

    @ColumnInfo(name = "next_review")
    val nextReview: Long? = null,

    @ColumnInfo(name = "reviews_left")
    val reviewsLeft: Int = NUMBER_OF_REVIEWS_REQUIRED,

    @ColumnInfo(name = "reviews_done")
    val reviewsDone: Int = 0
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

const val NUMBER_OF_REVIEWS_REQUIRED = 500 // TODO set to 5 or something when releasing
