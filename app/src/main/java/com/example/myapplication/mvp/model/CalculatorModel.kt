package com.example.myapplication.mvp.model

import com.example.myapplication.utils.EMPTY_STRING
import com.example.myapplication.utils.ZERO_FLOAT_RESULT

class CalculatorModel {
    var operatorOne: String = EMPTY_STRING
    var operatorTwo: String = EMPTY_STRING
    var operand: String = EMPTY_STRING
    var result: Float = ZERO_FLOAT_RESULT

    fun resetAll() {
        operatorOne = EMPTY_STRING
        operatorTwo = EMPTY_STRING
        operand = EMPTY_STRING
        result = ZERO_FLOAT_RESULT
    }

    fun resetVisor() {
        operatorOne = EMPTY_STRING
        operatorTwo = EMPTY_STRING
        operand = EMPTY_STRING
    }
}