package com.github.vnord.derdiedas.data.repository

import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.data.entity.relation.CategoryNounCrossRef
import com.github.vnord.derdiedas.data.source.NounDao
import com.github.vnord.derdiedas.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NounRepositoryImpl(private val dao: NounDao) : NounRepository {
    override fun getNouns(category: Category, limit: Int?): Flow<List<Noun>> =
        dao.getNounsOfCategory(category.categoryName).map {
            it.nouns
        }

    override suspend fun insertNoun(noun: Noun, category: Category) {
        dao.insertNoun(noun)
        dao.insertCategory(category)
        dao.insertCategoryNounCrossRef(CategoryNounCrossRef(category.categoryName, noun.nounString))
    }

    override fun getCategories(): Flow<List<Category>> = dao.getCategories()
}
