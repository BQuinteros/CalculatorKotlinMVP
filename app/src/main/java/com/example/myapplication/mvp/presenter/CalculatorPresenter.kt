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
        if (model.result == ZERO_FLOAT_RESULT) {
            if (model.operand.isEmpty()) {
                model.operatorOne = "${model.operatorOne}$number"
                view.setVisor(model.operatorOne)
            } else {
                model.operatorTwo = "${model.operatorTwo}$number"
                view.setVisor("${model.operatorOne}${model.operand}${model.operatorTwo}")
            }
            if (number == POINT) {
                view.diseablePoint()
            }
        } else {
            model.result = ZERO_FLOAT_RESULT
            model.operatorOne = "${model.operatorOne}$number"
            view.setVisor(model.operatorOne)
        }
    }

    fun onOperatorPressed(operator: String) {
        if (model.result == ZERO_FLOAT_RESULT) {
            if (model.operand.isNotEmpty()) {
                model.operatorTwo = EMPTY_STRING
            }
            model.operand = operator
            view.setVisor("${model.operatorOne}$operator")
            view.activePoint()
        } else if (model.operand.isEmpty()) {
            model.operand = operator
            model.operatorOne = model.result.toString()
            model.result = ZERO_FLOAT_RESULT
            view.setVisor("${model.operatorOne}$operator")
            view.setResult(EMPTY_STRING)
        }
    }

    fun onEqualPressed() {
        if (model.operatorOne.isNotEmpty() && model.operatorTwo.isNotEmpty()) {
            if (model.operatorOne != POINT && model.operatorTwo != POINT) {
                when (model.operand) {
                    PLUS -> {
                        model.result = model.operatorOne.toFloat() + model.operatorTwo.toFloat()
                        view.setResult(model.result.toString())
                    }
                    SUBTRACT -> {
                        model.result = model.operatorOne.toFloat() - model.operatorTwo.toFloat()
                        view.setResult(model.result.toString())
                    }
                    DIVIDE -> {
                        if (model.operatorTwo.toFloat() != ZERO_FLOAT_RESULT) {
                            model.result = model.operatorOne.toFloat() / model.operatorTwo.toFloat()
                            view.setResult(model.result.toString())
                        } else {
                            view.showToastError(TOAST_ZERO_DIVIDE)
                        }
                    }
                    MULTIPLY -> {
                        model.result = model.operatorOne.toFloat() * model.operatorTwo.toFloat()
                        view.setResult(model.result.toString())
                    }
                }
                model.resetVisor()
                view.activePoint()
            }
        }
    }

    fun onClearPressed() {
        if (model.operatorTwo.isNotEmpty()) {
            model.operatorTwo = "${model.operatorTwo.subSequence(ZERO_INT, model.operatorTwo.length - ONE_INT)}"
            view.setVisor("${model.operatorOne}${model.operand}${model.operatorTwo}")
        } else if (model.operand.isNotEmpty()) {
            model.operand = EMPTY_STRING
            view.setVisor(model.operatorOne)
        } else if (model.operatorOne.isNotEmpty()) {
            model.operatorOne = "${model.operatorOne.subSequence(ZERO_INT, model.operatorOne.length - ONE_INT)}"
            view.setVisor(model.operatorOne)
        } else {
            view.setVisor(EMPTY_STRING)
        }
    }

    fun onClearAllPressed() {
        model.resetAll()
        view.setVisor(EMPTY_STRING)
        view.setResult(EMPTY_STRING)
    }
}