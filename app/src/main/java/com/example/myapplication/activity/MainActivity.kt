package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.myapplication.R
import com.example.myapplication.mvp.model.CalculatorModel
import com.example.myapplication.mvp.presenter.CalculatorPresenter
import com.example.myapplication.mvp.view.CalculatorView
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

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var presenter = CalculatorPresenter(CalculatorModel(), CalculatorView(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listenerButtonClick()
    }

    fun listenerButtonClick() {
        button_zero.setOnClickListener(this)
        button_one.setOnClickListener(this)
        button_two.setOnClickListener(this)
        button_three.setOnClickListener(this)
        button_four.setOnClickListener(this)
        button_five.setOnClickListener(this)
        button_six.setOnClickListener(this)
        button_seven.setOnClickListener(this)
        button_eight.setOnClickListener(this)
        button_nine.setOnClickListener(this)
        button_plus.setOnClickListener(this)
        button_equal.setOnClickListener(this)
        button_less.setOnClickListener(this)
        button_point.setOnClickListener(this)
        button_divide.setOnClickListener(this)
        button_multiplication.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val pressed: String = (v as Button).text.toString()
        when (v) {
            button_zero, button_one, button_two, button_three, button_four,
            button_five, button_six, button_seven, button_eight,
            button_nine, button_point -> presenter?.onNumberPressed(pressed)
            button_plus, button_less, button_multiplication,
            button_divide -> presenter?.onOperatorPressed(pressed)
            button_equal -> presenter?.onEqualPressed()
        }
    }
}
