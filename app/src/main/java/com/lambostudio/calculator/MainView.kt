package com.lambostudio.calculator

interface MainView {
    fun setUpCalculator()
    fun pressNumber(number: Int)
    fun updateText()
    fun clearScreen()
}