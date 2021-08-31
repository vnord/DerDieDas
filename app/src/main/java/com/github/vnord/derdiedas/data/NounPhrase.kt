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

enum class Gender(val str: String) { DER("der"), DIE("die"), DAS("das") }

const val NUMBER_OF_REVIEWS_REQUIRED = 5
