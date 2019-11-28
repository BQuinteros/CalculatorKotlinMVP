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
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CalculatorPresenterTest {

    private var presenter: CalculatorPresenter? = null
    @Mock  lateinit var mockModel: CalculatorModel
    @Mock lateinit var mockView: CalculatorView
    @Mock lateinit var mockActivity: MainActivity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(mockView.activity).thenReturn(mockActivity)
        presenter = CalculatorPresenter(mockModel,mockView)
    }

    @Test
    fun resultZeroAndOperandEmptyOnNumberPressed(){
        whenever(mockModel.result).thenReturn(ZERO_FLOAT_RESULT)
        whenever(mockModel.operand).thenReturn(EMPTY_STRING)
        whenever(mockModel.operatorOne).thenReturn(EMPTY_STRING)
        presenter?.onNumberPressed(ONE_INT.toString())
        verify(mockModel).operatorOne = ONE_INT.toString()
        verify(mockView).setVisor(mockModel.operatorOne)
    }

    @Test
    fun resultZeroAndOperandEmptyAndPointOnNumberPressed(){
        whenever(mockModel.result).thenReturn(ZERO_FLOAT_RESULT)
        whenever(mockModel.operand).thenReturn(EMPTY_STRING)
        whenever(mockModel.operatorOne).thenReturn(EMPTY_STRING)
        presenter?.onNumberPressed(POINT)
        verify(mockModel).operatorOne = POINT
        verify(mockView).setVisor(mockModel.operatorOne)
        verify(mockView).diseablePoint()
    }

    @Test
    fun resultZeroAndOperandNotEmptyOnNumberPressed(){
        whenever(mockModel.operand).thenReturn(PLUS)
        whenever(mockModel.result).thenReturn(ZERO_FLOAT_RESULT)
        whenever(mockModel.operatorOne).thenReturn(POINT)
        whenever(mockModel.operatorTwo).thenReturn(EMPTY_STRING)
        presenter?.onNumberPressed(ONE_INT.toString())
        verify(mockModel).operatorTwo = ONE_INT.toString()
        verify(mockView).setVisor("${mockModel.operatorOne}$PLUS${mockModel.operatorTwo}")
    }

    @Test
    fun resultNotZeroOnNumberPressed(){
        whenever(mockModel.result).thenReturn(ONE_INT.toFloat())
        whenever(mockModel.operatorOne).thenReturn(EMPTY_STRING)
        presenter?.onNumberPressed(ONE_INT.toString())
        verify(mockModel).operatorOne = ONE_INT.toString()
    }

    @Test
    fun resultZeroAndOperandEmptyOnOperatorPressed(){
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operatorTwo).thenReturn(EMPTY_STRING)
        whenever(mockModel.result).thenReturn(ZERO_FLOAT_RESULT)
        whenever(mockModel.operand).thenReturn(PLUS)
        presenter?.onOperatorPressed(PLUS)
        verify(mockModel).operatorTwo = EMPTY_STRING
        verify(mockModel).operand = PLUS
        verify(mockView).setVisor("${mockModel.operatorOne}$PLUS")
        verify(mockView).activePoint()
    }

    @Test
    fun resultNotZeroAndOperandEmptyOnOperatorPressed() {
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operatorTwo).thenReturn(EMPTY_STRING)
        whenever(mockModel.result).thenReturn(ONE_INT.toFloat())
        whenever(mockModel.operand).thenReturn(EMPTY_STRING)
        presenter?.onOperatorPressed(PLUS)
        verify(mockModel).operatorOne = "${mockModel.operatorOne}$POINT$ZERO_FLOAT_RESULT"
        verify(mockModel).operand = PLUS
        verify(mockModel).result = ZERO_FLOAT_RESULT
        verify(mockView).setVisor("${mockModel.operatorOne}$PLUS")
    }

    @Test
    fun onClearAllPressedTest() {
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operatorTwo).thenReturn(ONE_INT.toString())
        whenever(mockModel.operand).thenReturn(PLUS)
        whenever(mockModel.result).thenReturn(ONE_INT.toFloat())
        presenter?.onClearAllPressed()
        verify(mockModel).operatorOne = EMPTY_STRING
        verify(mockModel).operatorTwo = EMPTY_STRING
        verify(mockModel).operand = EMPTY_STRING
        verify(mockModel).result = ZERO_FLOAT_RESULT
    }

    @Test
    fun operatorTwoNotEmptyOnClearPressed(){
        whenever(mockModel.operatorTwo).thenReturn(ONE_INT.toString())
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operand).thenReturn(PLUS)
        presenter?.onClearPressed()
        verify(mockModel).operatorTwo = EMPTY_STRING
    }

    @Test
    fun operandNotEmptyOnClearPressed(){
        whenever(mockModel.operatorTwo).thenReturn(EMPTY_STRING)
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operand).thenReturn(PLUS)
        presenter?.onClearPressed()
        verify(mockModel).operand = EMPTY_STRING
    }

    @Test
    fun operandOneNotEmptyOnClearPressed(){
        whenever(mockModel.operatorTwo).thenReturn(EMPTY_STRING)
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operand).thenReturn(EMPTY_STRING)
        presenter?.onClearPressed()
        verify(mockModel).operatorOne = EMPTY_STRING
    }

    @Test
    fun allEmptyOnClearPressed(){
        whenever(mockModel.operatorTwo).thenReturn(EMPTY_STRING)
        whenever(mockModel.operatorOne).thenReturn(EMPTY_STRING)
        whenever(mockModel.operand).thenReturn(EMPTY_STRING)
        presenter?.onClearPressed()
        verify(mockView).setVisor(EMPTY_STRING)
    }

    @Test
    fun plusOnEqualPressed(){
        whenever(mockModel.operatorTwo).thenReturn(ONE_INT.toString())
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operand).thenReturn(PLUS)
        presenter?.onEqualPressed()
        verify(mockModel).result = ONE_INT.toFloat() + ONE_INT.toFloat()
    }

    @Test
    fun subtractOnEqualPressed(){
        whenever(mockModel.operatorTwo).thenReturn(ONE_INT.toString())
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operand).thenReturn(SUBTRACT)
        presenter?.onEqualPressed()
        verify(mockModel).result = ONE_INT.toFloat() - ONE_INT.toFloat()
    }

    @Test
    fun divideOnEqualPressed(){
        whenever(mockModel.operatorTwo).thenReturn(ONE_INT.toString())
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operand).thenReturn(DIVIDE)
        presenter?.onEqualPressed()
        verify(mockModel).result = ONE_INT.toFloat() / ONE_INT.toFloat()
    }

    @Test
    fun divideWithZeroOnEqualPressed(){
        whenever(mockModel.operatorTwo).thenReturn(ZERO_FLOAT_RESULT.toString())
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operand).thenReturn(DIVIDE)
        presenter?.onEqualPressed()
        verify(mockView).showToastError(TOAST_ZERO_DIVIDE)
    }

    @Test
    fun multiplyOnEqualPressed(){
        whenever(mockModel.operatorTwo).thenReturn(ONE_INT.toString())
        whenever(mockModel.operatorOne).thenReturn(ONE_INT.toString())
        whenever(mockModel.operand).thenReturn(MULTIPLY)
        presenter?.onEqualPressed()
        verify(mockModel).result = ONE_INT.toFloat() * ONE_INT.toFloat()
    }
}
