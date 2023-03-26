package com.github.vnord.derdiedas.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.data.entity.relation.CategoryNounCrossRef
import com.github.vnord.derdiedas.domain.model.Categories
import com.opencsv.CSVReaderBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

@Database(
    entities = [
        Noun::class,
        Category::class,
        CategoryNounCrossRef::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class NounDatabase : RoomDatabase() {
    abstract val nounDao: NounDao

    companion object {
        private const val DATABASE_NAME = "noun_db"

        suspend fun create(context: Context): NounDatabase {
            val db = Room.databaseBuilder(context, NounDatabase::class.java, DATABASE_NAME).build()
            withContext(Dispatchers.IO) {
                if (db.nounDao.getNounCount().firstOrNull() == 0) {
                    seedDatabase(context, db)
                }
            }
            return db
        }

        private suspend fun seedDatabase(context: Context, db: NounDatabase) {
            val inputStream = context.assets.open("top_100.csv")
            val reader = InputStreamReader(inputStream)
            val csvReader = CSVReaderBuilder(reader).withSkipLines(1).build()

            val nouns = csvReader.readAll().map {
                val nounString = it[0]
                val gender = when (it[1]) {
                    "neuter" -> Noun.Gender.NEUTER
                    "masculine" -> Noun.Gender.MASCULINE
                    "feminine" -> Noun.Gender.FEMININE
                    else -> Noun.Gender.NEUTER
                }
                Noun(nounString, gender)
            }

            val dao = db.nounDao

            dao.insertCategory(Categories.MyNouns)
            dao.insertCategory(Categories.Top100)
            nouns.forEach {
                dao.insertNoun(it)
                dao.insertCategoryNounCrossRef(
                    CategoryNounCrossRef(
                        Categories.Top100.categoryName,
                        it.nounString,
                    ),
                )
            }
        }
    }
}
