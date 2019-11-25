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

    private var presenter : CalculatorPresenter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = CalculatorPresenter(CalculatorModel(), CalculatorView(this))

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
        val pressed : String = (v as Button).text.toString()
        when (v.id){
            R.id.button_zero, R.id.button_one, R.id.button_two, R.id.button_three, R.id.button_four,
            R.id.button_five, R.id.button_five, R.id.button_six, R.id.button_seven, R.id.button_eight,
            R.id.button_nine, R.id.button_point -> presenter?.onNumberPressed(pressed)
            R.id.button_plus, R.id.button_plus, R.id.button_less, R.id.button_multiplication,
            R.id.button_divide -> presenter?.onOperatorPressed(pressed)
            R.id.button_equal -> presenter?.onEqualPressed()
            else -> {
            }
        }
    }


}
