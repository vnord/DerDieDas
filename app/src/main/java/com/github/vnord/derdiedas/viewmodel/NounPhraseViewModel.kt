package com.github.vnord.derdiedas

import androidx.lifecycle.*
import com.github.vnord.derdiedas.data.NounPhrase
import com.github.vnord.derdiedas.data.NounPhraseDao
import com.github.vnord.derdiedas.data.Gender
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NounPhraseViewModel(private val nounPhraseDao: NounPhraseDao) : ViewModel() {

    fun allNounPhrases(): Flow<List<NounPhrase>> = nounPhraseDao.getNounPhrases()

    private fun insertNounPhrase(nounPhrase: NounPhrase) {
        viewModelScope.launch {
            nounPhraseDao.insert(nounPhrase)
        }
    }

    fun addNewNounPhrase(noun: String, gender: Gender) {
        insertNounPhrase(NounPhrase(noun, gender))
    }

    fun isEntryValid(noun: String) = noun.isNotBlank()

}

class NounPhraseViewModelFactory(private val nounPhraseDao: NounPhraseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NounPhraseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NounPhraseViewModel(nounPhraseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
