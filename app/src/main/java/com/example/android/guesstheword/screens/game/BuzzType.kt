package com.example.android.guesstheword.screens.game

internal val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
internal val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
internal val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
internal val NO_BUZZ_PATTERN = longArrayOf(0)

enum class BuzzType(val pattern: LongArray) {
    CORRECT(CORRECT_BUZZ_PATTERN),
    GAME_OVER(GAME_OVER_BUZZ_PATTERN),
    COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
    NO_BUZZ(NO_BUZZ_PATTERN)
}