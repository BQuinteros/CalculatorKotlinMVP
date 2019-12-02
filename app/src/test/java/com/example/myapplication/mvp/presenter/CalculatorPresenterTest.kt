package com.example.myapplication.mvp.presenter

import com.example.myapplication.activity.MainActivity
import com.example.myapplication.mvp.model.CalculatorModel
import com.example.myapplication.mvp.view.CalculatorView
import com.example.myapplication.utils.DIVIDE
import com.example.myapplication.utils.EMPTY_STRING
import com.example.myapplication.utils.MULTIPLY
import com.example.myapplication.utils.ONE_INT
import com.example.myapplication.utils.PLUS
import com.example.myapplication.utils.POINT
import com.example.myapplication.utils.SUBTRACT
import com.example.myapplication.utils.TOAST_ZERO_DIVIDE
import com.example.myapplication.utils.ZERO_FLOAT_RESULT
import com.nhaarman.mockitokotlin2.mockingDetails
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class CalculatorPresenterTest {

    private lateinit var presenter: CalculatorPresenter
    @Spy
    lateinit var mockModel: CalculatorModel
    @Mock
    lateinit var mockView: CalculatorView
    @Mock
    lateinit var mockActivity: MainActivity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(mockView.activity).thenReturn(mockActivity)
        presenter = CalculatorPresenter(mockModel, mockView)
    }

    @Test
    fun resultZeroAndOperandEmptyOnNumberPressed() {
        mockModel.result = ZERO_FLOAT_RESULT
        mockModel.operatorOne = EMPTY_STRING
        mockModel.operatorTwo = EMPTY_STRING
        presenter.onNumberPressed(ONE_INT.toString())
        verify(mockModel).operatorOne = ONE_INT.toString()
        verify(mockView).setVisor(ONE_INT.toString())
        assertEquals(ONE_INT.toString(), mockModel.operatorOne)
    }

    @Test
    fun resultZeroAndOperandEmptyAndPointOnNumberPressed() {
        mockModel.result = ZERO_FLOAT_RESULT
        mockModel.operatorOne = EMPTY_STRING
        mockModel.operand = EMPTY_STRING
        presenter.onNumberPressed(POINT)
        verify(mockModel).operatorOne = POINT
        verify(mockView).setVisor(mockModel.operatorOne)
        verify(mockView).diseablePoint()
        assertEquals(POINT, mockModel.operatorOne)
    }

    @Test
    fun resultZeroAndOperandNotEmptyOnNumberPressed() {
        mockModel.result = ZERO_FLOAT_RESULT
        mockModel.operatorOne = POINT
        mockModel.operand = PLUS
        mockModel.operatorTwo = EMPTY_STRING
        presenter.onNumberPressed(ONE_INT.toString())
        verify(mockModel).operatorTwo = ONE_INT.toString()
        verify(mockView).setVisor("${mockModel.operatorOne}$PLUS${mockModel.operatorTwo}")
        assertEquals(ONE_INT.toString(), mockModel.operatorTwo)
    }

    @Test
    fun resultNotZeroOnNumberPressed() {
        mockModel.result = ONE_INT.toFloat()
        mockModel.operatorOne = EMPTY_STRING
        mockModel.operatorTwo = EMPTY_STRING
        presenter.onNumberPressed(ONE_INT.toString())
        verify(mockModel).operatorOne = ONE_INT.toString()
        assertEquals(ONE_INT.toString(), mockModel.operatorOne)
    }

    @Test
    fun resultZeroAndOperandEmptyOnOperatorPressed() {
        mockModel.result = ZERO_FLOAT_RESULT
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operatorTwo = EMPTY_STRING
        mockModel.operand = PLUS
        presenter.onOperatorPressed(PLUS)
        verify(mockView).setVisor("${mockModel.operatorOne}$PLUS")
        verify(mockView).activePoint()
        assertEquals(mockModel.operatorTwo, EMPTY_STRING)
        assertEquals(mockModel.operand, PLUS)
    }

    @Test
    fun resultNotZeroAndOperandEmptyOnOperatorPressed() {
        mockModel.result = ONE_INT.toFloat()
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operatorTwo = EMPTY_STRING
        mockModel.operand = EMPTY_STRING
        presenter.onOperatorPressed(PLUS)
        verify(mockView).setVisor("${mockModel.operatorOne}$PLUS")
        assertEquals("${mockModel.operatorOne}", mockModel.operatorOne)
        assertEquals(PLUS, mockModel.operand)
    }

    @Test
    fun onClearAllPressedTest() {
        mockModel.result = ONE_INT.toFloat()
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operatorTwo = ONE_INT.toString()
        mockModel.operand = PLUS
        presenter.onClearAllPressed()
        verify(mockModel).operatorOne = EMPTY_STRING
        verify(mockModel).operatorTwo = EMPTY_STRING
        verify(mockModel).operand = EMPTY_STRING
        verify(mockModel).result = ZERO_FLOAT_RESULT
        assertEquals(EMPTY_STRING, mockModel.operatorOne)
        assertEquals(EMPTY_STRING, mockModel.operatorTwo)
        assertEquals(EMPTY_STRING, mockModel.operand)
        assertEquals(ZERO_FLOAT_RESULT, mockModel.result)
    }

    @Test
    fun operatorTwoNotEmptyOnClearPressed() {
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operatorTwo = ONE_INT.toString()
        mockModel.operand = PLUS
        presenter.onClearPressed()
        assertEquals(mockModel.operatorTwo, EMPTY_STRING)
    }

    @Test
    fun operandNotEmptyOnClearPressed() {
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operatorTwo = EMPTY_STRING
        mockModel.operand = PLUS
        presenter.onClearPressed()
        verify(mockModel).operand = EMPTY_STRING
        assertEquals(EMPTY_STRING, mockModel.operand)
    }

    @Test
    fun operandOneNotEmptyOnClearPressed() {
        mockModel.operatorTwo = EMPTY_STRING
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operand = EMPTY_STRING
        presenter.onClearPressed()
        verify(mockModel).operatorOne = EMPTY_STRING
        assertEquals(EMPTY_STRING, mockModel.operatorOne)
    }

    @Test
    fun allEmptyOnClearPressed() {
        mockModel.operatorTwo = EMPTY_STRING
        mockModel.operatorOne = EMPTY_STRING
        mockModel.operand = EMPTY_STRING
        presenter.onClearPressed()
        verify(mockView).setVisor(EMPTY_STRING)
    }

    @Test
    fun plusOnEqualPressed() {
        mockModel.operatorTwo = ONE_INT.toString()
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operand = PLUS
        presenter.onEqualPressed()
        verify(mockModel).result = ONE_INT.toFloat() + ONE_INT.toFloat()
        assertEquals(ONE_INT.toFloat() + ONE_INT.toFloat(), mockModel.result)
    }

    @Test
    fun subtractOnEqualPressed() {
        mockModel.operatorTwo = ONE_INT.toString()
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operand = SUBTRACT
        presenter.onEqualPressed()
        verify(mockModel).result = ONE_INT.toFloat() - ONE_INT.toFloat()
        assertEquals(ZERO_FLOAT_RESULT, mockModel.result)

    }

    @Test
    fun divideOnEqualPressed() {
        mockModel.operatorTwo = ONE_INT.toString()
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operand = DIVIDE
        presenter.onEqualPressed()
        verify(mockModel).result = ONE_INT.toFloat() / ONE_INT.toFloat()
        assertEquals(mockModel.result, ONE_INT.toFloat())
    }

    @Test
    fun divideWithZeroOnEqualPressed() {
        mockModel.operatorTwo = ZERO_FLOAT_RESULT.toString()
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operand = DIVIDE
        presenter.onEqualPressed()
        verify(mockView).showToastError(TOAST_ZERO_DIVIDE)
    }

    @Test
    fun multiplyOnEqualPressed() {
        mockModel.operatorTwo = ONE_INT.toString()
        mockModel.operatorOne = ONE_INT.toString()
        mockModel.operand = MULTIPLY
        presenter.onEqualPressed()
        verify(mockModel).result = ONE_INT.toFloat() * ONE_INT.toFloat()
        assertEquals(mockModel.result, ONE_INT.toFloat())
    }
}
