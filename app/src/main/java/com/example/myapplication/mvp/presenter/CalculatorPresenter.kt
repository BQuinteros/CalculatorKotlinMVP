package com.example.myapplication.mvp.presenter

import com.example.myapplication.mvp.model.CalculatorModel
import com.example.myapplication.mvp.view.CalculatorView
import com.example.myapplication.utils.DIVIDE
import com.example.myapplication.utils.EMPTY_STRING
import com.example.myapplication.utils.MULTIPLICATION
import com.example.myapplication.utils.PLUS
import com.example.myapplication.utils.SUBTRACT

class CalculatorPresenter(val model: CalculatorModel, val view: CalculatorView) {

    fun onNumberPressed(number: String) {
        if (model.getOperand() == EMPTY_STRING) {
            model.setOperator1("${model.getOperator1()}$number")
            view.setVisor(model.getOperator1())
        } else {
            model.setOperator2("${model.getOperator2()}$number")
            view.setVisor("${model.getOperator1()}${model.getOperand()}${model.getOperator2()}")
        }
    }

    fun onOperatorPressed(operator: String) {
        if (model.getOperator1() != EMPTY_STRING) {
            if (model.getOperand() == EMPTY_STRING) {
                model.setOperand(operator)
                view.setVisor("${model.getOperator1()}$operator")
            } else {
                model.setOperator2(EMPTY_STRING)
                model.setOperand(operator)
                view.setVisor("${model.getOperator1()}$operator")
            }
        }
    }

    fun onEqualPressed() {
        when (model.getOperand()) {
            PLUS -> {
                model.setResult(model.getOperator1().toFloat() + model.getOperator2().toFloat())
                view.setResult(model.getResult().toString())
            }
            SUBTRACT -> {
                model.setResult(model.getOperator1().toFloat() - model.getOperator2().toFloat())
                view.setResult(model.getResult().toString())
            }
            DIVIDE -> {
                model.setResult(model.getOperator1().toFloat() / model.getOperator2().toFloat())
                view.setResult(model.getResult().toString())
            }
            MULTIPLICATION -> {
                model.setResult(model.getOperator1().toFloat() * model.getOperator2().toFloat())
                view.setResult(model.getResult().toString())
            }
        }
        model.setOperator1(EMPTY_STRING)
        model.setOperator2(EMPTY_STRING)
        model.setOperand(EMPTY_STRING)
    }
}