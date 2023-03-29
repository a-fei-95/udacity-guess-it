package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _word = MutableLiveData<String>()
    val word: LiveData<String> get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private lateinit var wordList: MutableList<String>

    private val _gameFinishEvent = MutableLiveData<Boolean>()
    val gameFinishEvent: LiveData<Boolean> get() = _gameFinishEvent

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTimeString: LiveData<String> = Transformations.map(_currentTime) { time ->
        DateUtils.formatElapsedTime(time / ONE_SECOND)
    }

    private val _buzzEvent = MutableLiveData<BuzzType>()
    val buzzEvent: LiveData<BuzzType> get() = _buzzEvent

    init {
        _word.value = ""
        _score.value = 0
        _gameFinishEvent.value = false
        _currentTime.value = COUNTDOWN_TIME
        _buzzEvent.value = BuzzType.NO_BUZZ

        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = _currentTime.value?.minus(ONE_SECOND)
                _buzzEvent.value = BuzzType.COUNTDOWN_PANIC
            }

            override fun onFinish() {
                _gameFinishEvent.value = true
                _buzzEvent.value = BuzzType.GAME_OVER
            }
        }

        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = _score.value?.plus(1)
        _buzzEvent.value = BuzzType.CORRECT
        nextWord()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) resetList()
        _word.value = wordList.removeAt(0)
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

    fun onBuzzComplete() {
        _buzzEvent.value = BuzzType.NO_BUZZ
    }

    companion object {
        const val DONE = 0L
        const val ONE_SECOND = 1000L
        const val COUNTDOWN_TIME = 10000L
    }
}