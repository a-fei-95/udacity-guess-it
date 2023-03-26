package com.example.android.guesstheword.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel() {

    private var _word = MutableLiveData<String>()
    val word: LiveData<String> get() = _word

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private lateinit var wordList: MutableList<String>

    private var _gameFinishEvent = MutableLiveData<Boolean>()
    val gameFinishEvent: LiveData<Boolean> get() = _gameFinishEvent

    init {
        Timber.i("GameViewModel created!")

        _word.value = ""
        _score.value = 0
        _gameFinishEvent.value = false

        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("GameViewModel destroyed!")
    }

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = _score.value?.plus(1)
        nextWord()
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            _gameFinishEvent.value = true
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    fun onGameFinishComplete() {
        _gameFinishEvent.value = false
    }
}