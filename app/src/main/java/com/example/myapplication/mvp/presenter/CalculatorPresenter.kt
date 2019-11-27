package com.example.myapplication.mvp.presenter

import com.example.myapplication.mvp.model.CalculatorModel
import com.example.myapplication.mvp.view.CalculatorView
import com.example.myapplication.utils.DIVIDE
import com.example.myapplication.utils.EMPTY_STRING
import com.example.myapplication.utils.MULTIPLY
import com.example.myapplication.utils.ONE_INT
import com.example.myapplication.utils.ZERO_FLOAT_RESULT
import com.example.myapplication.utils.PLUS
import com.example.myapplication.utils.POINT
import com.example.myapplication.utils.SUBTRACT
import com.example.myapplication.utils.TOAST_ZERO_DIVIDE
import com.example.myapplication.utils.ZERO_INT

class CalculatorPresenter(val model: CalculatorModel, val view: CalculatorView) {

    fun onNumberPressed(number: String) {
        if (model.getResult() == ZERO_FLOAT_RESULT) {
            if (model.getOperand().isEmpty()) {
                model.setOperatorOne("${model.getOperatorOne()}$number")
                view.setVisor(model.getOperatorOne())
            } else {
                model.setOperatorTwo("${model.getOperatorTwo()}$number")
                view.setVisor("${model.getOperatorOne()}${model.getOperand()}${model.getOperatorTwo()}")
            }
            if (number == POINT)
                view.diseablePoint()
        } else {
            model.setResult(ZERO_FLOAT_RESULT)
            model.setOperatorOne("${model.getOperatorOne()}$number")
            view.setVisor(model.getOperatorOne())
        }
    }

    fun onOperatorPressed(operator: String) {
        if (model.getResult() == ZERO_FLOAT_RESULT) {
            if (model.getOperatorOne().isNotEmpty() && model.getOperatorTwo().isEmpty()) {
                if (model.getOperand().isEmpty()) {
                    model.setOperand(operator)
                    view.setVisor("${model.getOperatorOne()}$operator")
                } else {
                    model.setOperatorTwo(EMPTY_STRING)
                    model.setOperand(operator)
                    view.setVisor("${model.getOperatorOne()}$operator")
                }
            }
            view.activePoint()
        } else if (model.getOperand().isEmpty()) {
            model.setOperand(operator)
            model.setOperatorOne(model.getResult().toString())
            model.setResult(ZERO_FLOAT_RESULT)
            view.setVisor("${model.getOperatorOne()}$operator")
            view.setResult(EMPTY_STRING)
        }
    }

    fun onEqualPressed() {
        if (model.getOperatorOne().isNotEmpty() && model.getOperatorTwo().isNotEmpty()) {
            if (model.getOperatorOne() != POINT && model.getOperatorTwo() != POINT) {
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
                        if (model.getOperatorTwo().toFloat() != ZERO_FLOAT_RESULT) {
                            model.setResult(model.getOperatorOne().toFloat() / model.getOperatorTwo().toFloat())
                            view.setResult(model.getResult().toString())
                        } else {
                            view.showToastError(TOAST_ZERO_DIVIDE)
                        }
                    }
                    MULTIPLY -> {
                        model.setResult(model.getOperatorOne().toFloat() * model.getOperatorTwo().toFloat())
                        view.setResult(model.getResult().toString())
                    }
                }
                model.setOperatorOne(EMPTY_STRING)
                model.setOperatorTwo(EMPTY_STRING)
                model.setOperand(EMPTY_STRING)
                view.activePoint()
            }
        }
    }

    fun onClearPressed() {
        if (model.getOperatorTwo().isNotEmpty()) {
            model.setOperatorTwo("${model.getOperatorTwo().subSequence(ZERO_INT, model.getOperatorTwo().length - ONE_INT)}")
            view.setVisor("${model.getOperatorOne()}${model.getOperand()}${model.getOperatorTwo()}")
        } else if (model.getOperand().isNotEmpty()) {
            model.setOperand(EMPTY_STRING)
            view.setVisor(model.getOperatorOne())
        } else if (model.getOperatorOne().isNotEmpty()) {
            model.setOperatorOne("${model.getOperatorOne().subSequence(ZERO_INT, model.getOperatorOne().length - ONE_INT)}")
            view.setVisor(model.getOperatorOne())
        } else {
            view.setVisor(EMPTY_STRING)
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