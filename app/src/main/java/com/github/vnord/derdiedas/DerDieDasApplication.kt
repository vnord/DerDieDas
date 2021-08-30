package com.github.vnord.derdiedas

import android.app.Application
import com.github.vnord.derdiedas.data.NounPhraseRoomDatabase

class DerDieDasApplication : Application() {
    val dataBase: NounPhraseRoomDatabase by lazy { NounPhraseRoomDatabase.getDatabase(this) }
}