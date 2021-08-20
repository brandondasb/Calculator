package com.lambostudio.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lambostudio.calculator.databinding.ActivityMainBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity(), PerformOperation, UpdateUi {

    private lateinit var binding: ActivityMainBinding
    var isLastButtonOperator = false
    var currentOperator = Operator.NONE
    var labelString = "" // will contain the string that is entered in the textView
    var savedNum = 0

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
                pressNumber(i)
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

    override fun pressEquals() {
        if (isLastButtonOperator) {
            return
        }
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

    override fun updateText() {
        if (labelString.length > 8) {
            pressClears()// reset calc
            binding.display.text = "tool long"
            return
        }

        val labelInt = labelString.toInt() // convert it to a number to prevent multiple 0 start
        labelString = labelInt.toString()

        if (currentOperator == Operator.NONE) {
            savedNum = labelInt
        }

        val df = DecimalFormat("#,###")
        binding.display.text = df.format(labelInt)
    }


    override fun pressNumber(number: Int) {
        val stringValue = number.toString()

        if (isLastButtonOperator) {
            isLastButtonOperator = false
            labelString = "0"
        }

        labelString = "$labelString$stringValue"
        updateText()
    }

    override fun changeOperator(operator: Operator) {
        if (savedNum == 0) {
            return
        }
        currentOperator = operator
        isLastButtonOperator = true
    }


}