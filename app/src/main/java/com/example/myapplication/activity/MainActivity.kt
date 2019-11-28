package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.mvp.model.CalculatorModel
import com.example.myapplication.mvp.presenter.CalculatorPresenter
import com.example.myapplication.mvp.view.CalculatorView
import kotlinx.android.synthetic.main.activity_main.button_clear
import kotlinx.android.synthetic.main.activity_main.button_clear_all
import kotlinx.android.synthetic.main.activity_main.button_divide
import kotlinx.android.synthetic.main.activity_main.button_eight
import kotlinx.android.synthetic.main.activity_main.button_equal
import kotlinx.android.synthetic.main.activity_main.button_five
import kotlinx.android.synthetic.main.activity_main.button_four
import kotlinx.android.synthetic.main.activity_main.button_less
import kotlinx.android.synthetic.main.activity_main.button_multiplication
import kotlinx.android.synthetic.main.activity_main.button_nine
import kotlinx.android.synthetic.main.activity_main.button_one
import kotlinx.android.synthetic.main.activity_main.button_plus
import kotlinx.android.synthetic.main.activity_main.button_point
import kotlinx.android.synthetic.main.activity_main.button_seven
import kotlinx.android.synthetic.main.activity_main.button_six
import kotlinx.android.synthetic.main.activity_main.button_three
import kotlinx.android.synthetic.main.activity_main.button_two
import kotlinx.android.synthetic.main.activity_main.button_zero

open class MainActivity : AppCompatActivity() {

    private var presenter = CalculatorPresenter(CalculatorModel(), CalculatorView(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listenerButtonClick()
    }

    fun listenerButtonClick() {
        button_zero.setOnClickListener { presenter.onNumberPressed(button_zero.text.toString()) }
        button_one.setOnClickListener { presenter.onNumberPressed(button_one.text.toString()) }
        button_two.setOnClickListener { presenter.onNumberPressed(button_two.text.toString()) }
        button_three.setOnClickListener { presenter.onNumberPressed(button_three.text.toString()) }
        button_four.setOnClickListener { presenter.onNumberPressed(button_four.text.toString()) }
        button_five.setOnClickListener { presenter.onNumberPressed(button_five.text.toString()) }
        button_six.setOnClickListener { presenter.onNumberPressed(button_six.text.toString()) }
        button_seven.setOnClickListener { presenter.onNumberPressed(button_seven.text.toString()) }
        button_eight.setOnClickListener { presenter.onNumberPressed(button_eight.text.toString()) }
        button_nine.setOnClickListener { presenter.onNumberPressed(button_nine.text.toString()) }
        button_plus.setOnClickListener { presenter.onOperatorPressed(button_plus.text.toString()) }
        button_equal.setOnClickListener { presenter.onEqualPressed() }
        button_less.setOnClickListener { presenter.onOperatorPressed(button_less.text.toString()) }
        button_point.setOnClickListener { presenter.onNumberPressed(button_point.text.toString()) }
        button_divide.setOnClickListener { presenter.onOperatorPressed(button_divide.text.toString()) }
        button_multiplication.setOnClickListener { presenter.onOperatorPressed(button_multiplication.text.toString()) }
        button_clear.setOnClickListener { presenter.onClearPressed()}
        button_clear_all.setOnClickListener { presenter.onClearAllPressed() }
    }
}
