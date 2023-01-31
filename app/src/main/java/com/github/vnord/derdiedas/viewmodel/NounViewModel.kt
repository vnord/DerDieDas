package com.github.vnord.derdiedas

import androidx.lifecycle.*
import com.github.vnord.derdiedas.data.Noun
import com.github.vnord.derdiedas.data.NounDao
import com.github.vnord.derdiedas.data.Gender
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NounViewModel(private val nounDao: NounDao) : ViewModel() {

    fun allNouns(): Flow<List<Noun>> = nounDao.getNouns()

    private fun insertNoun(noun: Noun) {
        viewModelScope.launch {
            nounDao.insert(noun)
        }
    }

    fun addNewNoun(noun: String, gender: Gender) {
        insertNoun(Noun(noun, gender))
    }

    fun isEntryValid(noun: String) = noun.isNotBlank()

    fun getNumberOfEligibleNouns() = nounDao.getNumberOfEligibleNouns().asLiveData()
}

class NounViewModelFactory(private val nounDao: NounDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NounViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NounViewModel(nounDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
