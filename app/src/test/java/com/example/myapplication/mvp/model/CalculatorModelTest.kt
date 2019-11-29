package com.example.myapplication.mvp.model

import com.example.myapplication.utils.ONE_INT
import com.example.myapplication.utils.PLUS
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorModelTest {

    lateinit var model: CalculatorModel

    @Before
    fun setUp() {
        model = CalculatorModel()
    }

    @Test
    fun operatorOneSetterWithANumber() {
        model.operatorOne = ONE_INT.toString()
        assertEquals(ONE_INT.toString(), model.operatorOne)
    }

    @Test
    fun operatorOneSetterWithTwoNumbers() {
        model.operatorOne = ONE_INT.toString()
        model.operatorOne = "${model.operatorOne}$ONE_INT"
        assertEquals(model.operatorOne, "$ONE_INT$ONE_INT")
    }

    @Test
    fun operatorTwoSetterWithANumber() {
        model.operatorTwo = ONE_INT.toString()
        assertEquals(ONE_INT.toString(), model.operatorTwo)
    }

    @Test
    fun operatorTwoSetterWithTwoNumbers() {
        model.operatorTwo = ONE_INT.toString()
        model.operatorTwo = "${model.operatorTwo}$ONE_INT"
        assertEquals(model.operatorTwo, "$ONE_INT$ONE_INT")
    }

    @Test
    fun operandSetter() {
        model.operand = PLUS
        assertEquals(PLUS, model.operand)
    }

    @Test
    fun resultSetter() {
        model.result = ONE_INT.toFloat()
        assertEquals(ONE_INT.toFloat(), model.result)
    }
}