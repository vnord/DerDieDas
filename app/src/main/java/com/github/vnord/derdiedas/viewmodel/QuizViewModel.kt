package com.github.vnord.derdiedas.viewmodel

import androidx.lifecycle.*
import com.github.vnord.derdiedas.data.NounPhrase
import com.github.vnord.derdiedas.data.NounPhraseDao
import com.github.vnord.derdiedas.data.Gender
import kotlinx.coroutines.launch

class QuizViewModel(private val nounPhraseDao: NounPhraseDao) : ViewModel() {

    private var _nounPhrase: MutableLiveData<NounPhrase> = MutableLiveData(NounPhrase("Please wait", Gender.DER))
    val nounPhrase: LiveData<NounPhrase>
        get() = _nounPhrase

    fun getRandomNounPhrase() {
        viewModelScope.launch {
            val numberOfNounPhrases = nounPhraseDao.getNumberOfNounPhrases()
            _nounPhrase.value = nounPhraseDao.getNthNounPhrase((0 until numberOfNounPhrases).random())
        }
    }
}

class QuizViewModelFactory(private val nounPhraseDao: NounPhraseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(nounPhraseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
