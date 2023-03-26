package com.github.vnord.derdiedas.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.data.entity.relation.CategoryNounCrossRef
import com.github.vnord.derdiedas.data.entity.relation.CategoryWithNouns
import com.github.vnord.derdiedas.data.entity.relation.NounWithCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface NounDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNoun(noun: Noun)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategoryNounCrossRef(crossRef: CategoryNounCrossRef)

    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<Category>>

    @Transaction
    @Query("SELECT * FROM category WHERE categoryName = :categoryName")
    fun getNounsOfCategory(categoryName: String): Flow<CategoryWithNouns>

    @Transaction
    @Query("SELECT * FROM noun WHERE nounString = :nounString")
    fun getCategoriesOfNoun(nounString: String): Flow<NounWithCategories>

//    @Query("SELECT * FROM noun")
//    fun getAllNouns(): Flow<List<Noun>>

//    @Query("SELECT * FROM noun LIMIT :limit")
//    fun getNouns(limit: Int): Flow<List<Noun>>

    // Only used for deciding whether to seed the database or not for now, so does
    // not need to be further abstracted until that changes
    @Query("SELECT COUNT(*) FROM noun")
    fun getNounCount(): Flow<Int>
}
