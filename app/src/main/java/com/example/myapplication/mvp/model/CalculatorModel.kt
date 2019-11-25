package com.example.myapplication.mvp.model

import com.example.myapplication.utils.EMPTY_STRING
import com.example.myapplication.utils.NULL_RESULT

class CalculatorModel {
    private var operator1: String = EMPTY_STRING
    private var operator2: String = EMPTY_STRING
    private var operand: String = EMPTY_STRING
    private var result: Float = NULL_RESULT

    fun getOperator1() = operator1

    fun getOperator2() = operator2

    fun getOperand() = operand

    fun getResult() = result

    fun setOperator1(operator: String) {
        this.operator1 = operator
    }

    fun setOperator2(operator: String) {
        this.operator2 = operator
    }

    fun setOperand(operand: String) {
        this.operand = operand
    }

    fun setResult(result: Float) {
        this.result = result
    }
}