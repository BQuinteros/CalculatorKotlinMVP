package com.example.myapplication.mvp.model

import com.example.myapplication.utils.ONE_INT
import com.example.myapplication.utils.PLUS
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class CalculatorModelTest {

    @Mock
    lateinit var mockModel : CalculatorModel

    @Before
    fun setUp(){
        mockModel = CalculatorModel()
    }

    @Test
    fun operatorOneSetterWithANumber(){
        mockModel.setOperatorOne(ONE_INT.toString())
        assertEquals(ONE_INT.toString(),mockModel.getOperatorOne())
    }

    @Test
    fun operatorOneSetterWithTwoNumbers(){
        mockModel.setOperatorOne(ONE_INT.toString())
        mockModel.setOperatorOne("${mockModel.getOperatorOne()}$ONE_INT")
        assertEquals(mockModel.getOperatorOne(),"$ONE_INT$ONE_INT")
    }

    @Test
    fun operatorTwoSetterWithANumber(){
        mockModel.setOperatorTwo(ONE_INT.toString())
        assertEquals(ONE_INT.toString(),mockModel.getOperatorTwo())
    }

    @Test
    fun operatorTwoSetterWithTwoNumbers(){
        mockModel.setOperatorTwo(ONE_INT.toString())
        mockModel.setOperatorTwo("${mockModel.getOperatorTwo()}$ONE_INT")
        assertEquals(mockModel.getOperatorTwo(),"$ONE_INT$ONE_INT")
    }

    @Test
    fun operandSetter(){
        mockModel.setOperand(PLUS)
        assertEquals(PLUS,mockModel.getOperand())
    }

    @Test
    fun resultSetter(){
        mockModel.setResult(ONE_INT.toFloat())
        assertEquals(ONE_INT.toFloat(),mockModel.getResult())
    }
}