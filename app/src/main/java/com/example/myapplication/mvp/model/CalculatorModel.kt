package com.example.myapplication.mvp.model

import com.example.myapplication.utils.EMPTY_STRING
import com.example.myapplication.utils.ZERO_FLOAT_RESULT

class CalculatorModel {
    private var operatorOne: String = EMPTY_STRING
    private var operatorTwo: String = EMPTY_STRING
    private var operand: String = EMPTY_STRING
    private var result: Float = ZERO_FLOAT_RESULT

    fun getOperatorOne() = operatorOne

    fun getOperatorTwo() = operatorTwo

    fun getOperand() = operand

    fun getResult() = result

    fun setOperatorOne(operator: String) {
        this.operatorOne = operator
    }

    fun setOperatorTwo(operator: String) {
        this.operatorTwo = operator
    }

    fun setOperand(operand: String) {
        this.operand = operand
    }

    fun setResult(result: Float) {
        this.result = result
    }
}