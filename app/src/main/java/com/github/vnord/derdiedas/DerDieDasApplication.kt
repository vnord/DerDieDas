package com.github.vnord.derdiedas

import android.app.Application
import com.github.vnord.derdiedas.data.ArticleRoomDatabase

class DerDieDasApplication : Application() {
    val dataBase: ArticleRoomDatabase by lazy { ArticleRoomDatabase.getDatabase(this) }
}