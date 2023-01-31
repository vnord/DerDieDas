package com.github.vnord.derdiedas

import android.app.Application
import com.github.vnord.derdiedas.data.NounRoomDatabase

class DerDieDasApplication : Application() {
    val dataBase: NounRoomDatabase by lazy { NounRoomDatabase.getDatabase(this) }
}