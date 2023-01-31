package com.github.vnord.derdiedas.viewmodel

import androidx.lifecycle.*
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.data.Noun
import com.github.vnord.derdiedas.data.NounDao
import kotlinx.coroutines.launch

class QuizViewModel(private val nounDao: NounDao) : ViewModel() {

    private var _noun: MutableLiveData<Noun> = MutableLiveData(null)
    val noun: LiveData<Noun>
        get() = _noun

    fun getRandomNoun() {
        viewModelScope.launch {
            val eligibleNouns = nounDao.getEligibleNouns()
            if (eligibleNouns.isNotEmpty()) _noun.value = eligibleNouns.random()
        }
    }

    fun updateNextReview(gotItRight: Boolean) {
        if (gotItRight) {
            viewModelScope.launch {
                noun.value?.let {
                    nounDao.update(
                        it.copy(
                            nextReview = System.currentTimeMillis().plus(10000),
                            reviewsDone = it.reviewsDone.inc()
                        )
                    )
                }
            }
        }
    }

    fun markThisNounAsDone() {
        viewModelScope.launch {
            noun.value?.let {
                nounDao.update(
                    it.copy(
                        reviewsLeft = 0,
                        nextReview = 0
                    )
                )
            }
        }
    }

    fun isGenderCorrect(gender: Gender): Boolean {
        return gender == noun.value?.gender
    }
}

class QuizViewModelFactory(private val nounDao: NounDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(nounDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
