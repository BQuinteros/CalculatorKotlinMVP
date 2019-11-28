package com.example.myapplication.mvp.presenter

import com.example.myapplication.activity.MainActivity
import com.example.myapplication.mvp.model.CalculatorModel
import com.example.myapplication.mvp.view.CalculatorView
import com.example.myapplication.utils.EMPTY_STRING
import com.example.myapplication.utils.ONE_INT
import com.example.myapplication.utils.PLUS
import com.example.myapplication.utils.POINT
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
        whenever(mockModel.operatorOne).thenReturn(EMPTY_STRING)
        presenter?.onNumberPressed(ONE_INT.toString())
        verify(mockModel).operatorOne = ONE_INT.toString()
        verify(mockView).setVisor(mockModel.operatorOne)
    }

    @Test
    fun resultZeroAndOperandEmptyAndPointOnNumberPressed(){
        whenever(mockModel.result).thenReturn(ZERO_FLOAT_RESULT)
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

    @Test resultZeroAndOperatorOneEmptyOnOperatorPressed(){

    }
}
