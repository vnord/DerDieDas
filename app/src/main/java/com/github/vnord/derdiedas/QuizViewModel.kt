package com.github.vnord.derdiedas

import androidx.lifecycle.*
import com.github.vnord.derdiedas.data.Article
import com.github.vnord.derdiedas.data.ArticleDao
import com.github.vnord.derdiedas.data.Gender
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class QuizViewModel(private val articleDao: ArticleDao) : ViewModel() {

    private val _article = MutableLiveData(getRandomArticle())
    private val _gender = MutableLiveData(_article.value!!.gender.str)
    val gender: LiveData<String>
        get() = _gender
    private val _actualArticle = MutableLiveData(_article.value!!.article)
    val actualArticle: LiveData<String>
        get() = _actualArticle


    fun getRandomArticle(): Article {
        val totalNumberOfArticles = articleDao.getNumberOfArticles()
        return articleDao.getNthArticle((0..totalNumberOfArticles).random())
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
