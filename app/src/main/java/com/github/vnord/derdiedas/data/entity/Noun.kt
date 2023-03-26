package com.github.vnord.derdiedas.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Noun(
    @PrimaryKey
    val nounString: String,
    val gender: Gender,
) {
    enum class Gender(val str: String) {
        MASCULINE("der"), FEMININE("die"), NEUTER("das")
    }
}
