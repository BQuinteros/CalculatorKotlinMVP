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
            model.setOperatorOne("${model.getOperatorOne()}$number")
            view.setVisor(model.getOperatorOne())
        } else {
            model.setOperatorTwo("${model.getOperatorTwo()}$number")
            view.setVisor("${model.getOperatorOne()}${model.getOperand()}${model.getOperatorTwo()}")
        }
    }

    fun onOperatorPressed(operator: String) {
        if (model.getOperatorOne() != EMPTY_STRING) {
            if (model.getOperand() == EMPTY_STRING) {
                model.setOperand(operator)
                view.setVisor("${model.getOperatorOne()}$operator")
            } else {
                model.setOperatorTwo(EMPTY_STRING)
                model.setOperand(operator)
                view.setVisor("${model.getOperatorOne()}$operator")
            }
        }
    }

    fun onEqualPressed() {
        when (model.getOperand()) {
            PLUS -> {
                model.setResult(model.getOperatorOne().toFloat() + model.getOperatorTwo().toFloat())
                view.setResult(model.getResult().toString())
            }
            SUBTRACT -> {
                model.setResult(model.getOperatorOne().toFloat() - model.getOperatorTwo().toFloat())
                view.setResult(model.getResult().toString())
            }
            DIVIDE -> {
                model.setResult(model.getOperatorOne().toFloat() / model.getOperatorTwo().toFloat())
                view.setResult(model.getResult().toString())
            }
            MULTIPLICATION -> {
                model.setResult(model.getOperatorOne().toFloat() * model.getOperatorTwo().toFloat())
                view.setResult(model.getResult().toString())
            }
        }
        model.setOperatorOne(EMPTY_STRING)
        model.setOperatorTwo(EMPTY_STRING)
        model.setOperand(EMPTY_STRING)
    }
}