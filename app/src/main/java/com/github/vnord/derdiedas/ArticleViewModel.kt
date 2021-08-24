package com.github.vnord.derdiedas

import androidx.lifecycle.*
import com.github.vnord.derdiedas.data.Article
import com.github.vnord.derdiedas.data.ArticleDao
import com.github.vnord.derdiedas.data.Gender
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ArticleViewModel(private val articleDao: ArticleDao) : ViewModel() {

    val genderButtonId by lazy {
        MutableLiveData<Int>()
    }

    val articleInputText by lazy {
        MutableLiveData<String?>()
    }


    private fun insertArticle(article: Article) {
        viewModelScope.launch {
            articleDao.insert(article)
        }
    }

    fun addNewArticle(article: String, gender: Gender) {
        insertArticle(Article(article, gender))
    }

    fun isEntryValid(article: String) = article.isNotBlank()

}
class ArticleViewModelFactory(private val articleDao: ArticleDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleViewModel(articleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
