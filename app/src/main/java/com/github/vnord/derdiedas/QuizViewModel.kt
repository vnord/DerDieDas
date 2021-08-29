package com.github.vnord.derdiedas

import androidx.lifecycle.*
import com.github.vnord.derdiedas.data.Article
import com.github.vnord.derdiedas.data.ArticleDao
import com.github.vnord.derdiedas.data.Gender
import kotlinx.coroutines.launch

class QuizViewModel(private val articleDao: ArticleDao) : ViewModel() {

    private var _article: MutableLiveData<Article> = MutableLiveData(Article("Please wait", Gender.DER))
    val article: LiveData<Article>
        get() = _article

    fun getRandomArticle() {
        viewModelScope.launch {
            val numberOfArticles = articleDao.getNumberOfArticles()
            _article.value = articleDao.getNthArticle((0 until numberOfArticles).random())
        }
    }
}

class QuizViewModelFactory(private val articleDao: ArticleDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(articleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
