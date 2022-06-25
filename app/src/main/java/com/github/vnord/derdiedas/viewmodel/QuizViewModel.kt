package com.github.vnord.derdiedas.viewmodel

import androidx.lifecycle.*
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.data.NUMBER_OF_REVIEWS_REQUIRED
import com.github.vnord.derdiedas.data.NounPhrase
import com.github.vnord.derdiedas.data.NounPhraseDao
import kotlinx.coroutines.launch

class QuizViewModel(private val nounPhraseDao: NounPhraseDao) : ViewModel() {

    private var _nounPhrase: MutableLiveData<NounPhrase> = MutableLiveData(null)
    val nounPhrase: LiveData<NounPhrase>
        get() = _nounPhrase

    fun getRandomNounPhrase() {
        viewModelScope.launch {
            val eligiblePhrases = nounPhraseDao.getEligibleNounPhrases()
            if (eligiblePhrases.isNotEmpty()) _nounPhrase.value = eligiblePhrases.random()
        }
    }

    fun updateNextReview(gotItRight: Boolean) {
        if (gotItRight) {
            viewModelScope.launch {
                nounPhrase.value?.let {
                    nounPhraseDao.update(
                        it.copy(
                            nextReview = System.currentTimeMillis().plus(10000),
                            reviewsDone = it.reviewsDone.inc()
                        )
                    )
                }
            }
        }
    }

    fun markThisNounPhraseAsDone() {
        viewModelScope.launch {
            nounPhrase.value?.let {
                nounPhraseDao.update(
                    it.copy(
                        reviewsLeft = 0,
                        nextReview = 0
                    )
                )
            }
        }
    }

    fun isGenderCorrect(gender: Gender): Boolean {
        return gender == nounPhrase.value?.gender
    }
}

class QuizViewModelFactory(private val nounPhraseDao: NounPhraseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(nounPhraseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
