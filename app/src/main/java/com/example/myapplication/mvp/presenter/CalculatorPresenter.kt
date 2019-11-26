package com.example.myapplication.mvp.presenter

import com.example.myapplication.mvp.model.CalculatorModel
import com.example.myapplication.mvp.view.CalculatorView
import com.example.myapplication.utils.DIVIDE
import com.example.myapplication.utils.EMPTY_STRING
import com.example.myapplication.utils.MULTIPLY
import com.example.myapplication.utils.ZERO_FLOAT_RESULT
import com.example.myapplication.utils.PLUS
import com.example.myapplication.utils.SUBTRACT

class CalculatorPresenter(val model: CalculatorModel, val view: CalculatorView) {

    fun onNumberPressed(number: String) {
        if (model.getOperand().isEmpty()) {
            model.setOperatorOne("${model.getOperatorOne()}$number")
            view.setVisor(model.getOperatorOne())
        } else {
            model.setOperatorTwo("${model.getOperatorTwo()}$number")
            view.setVisor("${model.getOperatorOne()}${model.getOperand()}${model.getOperatorTwo()}")
        }
    }

    fun onOperatorPressed(operator: String) {
        if (model.getOperatorOne().isNotEmpty()) {
            if (model.getOperand().isEmpty()) {
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
            MULTIPLY -> {
                model.setResult(model.getOperatorOne().toFloat() * model.getOperatorTwo().toFloat())
                view.setResult(model.getResult().toString())
            }
        }
        model.setOperatorOne(EMPTY_STRING)
        model.setOperatorTwo(EMPTY_STRING)
        model.setOperand(EMPTY_STRING)
    }

    fun onClearPressed() {
        if (model.getOperatorTwo().isNotEmpty()) {
            model.setOperatorTwo("${model.getOperatorTwo().subSequence(0, model.getOperatorTwo().length - 1)}")
            view.setVisor("${model.getOperatorOne()}${model.getOperand()}${model.getOperatorTwo()}")
        } else if (model.getOperand().isNotEmpty()) {
            model.setOperand(EMPTY_STRING)
            view.setVisor(model.getOperatorOne())
        } else if (model.getOperatorOne().isNotEmpty()) {
            model.setOperatorOne("${model.getOperatorOne().subSequence(0, model.getOperatorOne().length - 1)}")
            view.setVisor(model.getOperatorOne())
        }
    }

    fun onClearAllPressed() {
        model.setOperand(EMPTY_STRING)
        model.setOperatorOne(EMPTY_STRING)
        model.setOperatorTwo(EMPTY_STRING)
        model.setResult(ZERO_FLOAT_RESULT)
        view.setVisor(EMPTY_STRING)
        view.setResult(EMPTY_STRING)
    }
}