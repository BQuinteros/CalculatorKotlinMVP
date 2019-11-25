package com.example.myapplication.mvp.model

class CalculatorModel{
    private var operator1 : String = ""
    private var operator2 : String = ""
    private var operand : String = ""
    private var result : Float = 0F

    fun getOperator1() = operator1

    fun getOperator2() = operator2

    fun getOperand() = operand

    fun getResult() = result

    fun setOperator1(operator : String){
        this.operator1 = operator
    }

    fun setOperator2(operator : String){
        this.operator2 = operator
    }

    fun setOperand(operand: String){
        this.operand= operand
    }

    fun setResult(result : Float){
        this.result = result
    }
}