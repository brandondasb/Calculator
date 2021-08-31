package com.lambostudio.calculator;

class MainActivityPresenter(mainView: MainView,performOperation: PerformOperation) {
    val mainView = mainView
    val performOperation = performOperation

    fun clear() {
        mainView.clearScreen()
    }

    fun calculate() {
       performOperation.pressEquals()
    }

    fun setupUi() {
        mainView.setUpCalculator()
    }

    fun updateText() {
        mainView.updateText()
    }
}
