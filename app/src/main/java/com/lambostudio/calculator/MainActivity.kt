package com.lambostudio.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lambostudio.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var isLastButtonOperator = false
    var currentOperator = Operator.NONE
    var labelString = "" // will contain the string that is entered in the textView
    var savedNum = 0


    var isNewOperator = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupCalculator()
    }

    fun setupCalculator() {
        val allButtons = arrayOf(
            binding.zero,
            binding.one,
            binding.two,
            binding.three,
            binding.four,
            binding.five,
            binding.six,
            binding.seven,
            binding.height,
            binding.nine
        )

        for (i in allButtons.indices) {
            allButtons[i].setOnClickListener {
                didPressNumber(i)
                Log.e("###", "$i")
            }
        }
        binding.add.setOnClickListener { changeOperator(Operator.ADDITION) }
        binding.subtract.setOnClickListener { changeOperator(Operator.SUBSTRACT) }
        binding.multiply.setOnClickListener { changeOperator(Operator.MULTIPLY) }
        binding.divide.setOnClickListener { changeOperator(Operator.DIVIDE) }
        binding.equal.setOnClickListener { pressEquals() }
        binding.ac.setOnClickListener { pressClears() }
    }

    fun pressEquals() {
        if (isLastButtonOperator) {
pressNumber        }
        val labelInt = labelString.toInt()// second number

        when (currentOperator) {
            Operator.ADDITION -> savedNum += labelInt
            Operator.SUBSTRACT -> savedNum -= labelInt
            Operator.MULTIPLY -> savedNum *= labelInt
            Operator.DIVIDE -> savedNum /= labelInt
            Operator.NONE -> return
        }
        currentOperator = Operator.NONE
        labelString = "$savedNum"
        updateText()
        isLastButtonOperator = true
    }

    fun pressClears() {
        isLastButtonOperator = false
        currentOperator = Operator.NONE
        labelString = "" // will contain the string that is entered in the textView
        savedNum = 0
        binding.display.text = "0"
    }

    fun updateText() {
        val labelInt = labelString.toInt() // convert it to a number to prevent multiple 0 start
        labelString = labelInt.toString()

        if (currentOperator == Operator.NONE) {
            savedNum = labelInt
        }

        binding.display.text = labelString
    }

    fun changeOperator(operator: Operator) {
        if (savedNum == 0) {
            return
        }
        currentOperator = operator
        isLastButtonOperator = true
    }

    fun didPressNumber(number: Int) {
        val stringValue = number.toString()

        if (isLastButtonOperator) {
            isLastButtonOperator = false
            labelString = "0"
        }

        labelString = "$labelString$stringValue"
        updateText()
    }


}