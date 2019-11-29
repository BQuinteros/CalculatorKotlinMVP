package com.example.myapplication.mvp.view

import android.app.Activity
import android.widget.Toast
import com.example.myapplication.mvp.view.base.ActivityView
import kotlinx.android.synthetic.main.activity_main.button_point
import kotlinx.android.synthetic.main.activity_main.text_result
import kotlinx.android.synthetic.main.activity_main.text_visor

open class CalculatorView(activity: Activity) : ActivityView(activity) {

    fun setVisor(operation: String) {
        activity?.text_visor?.text = operation
    }

    fun setResult(result: String) {
        activity?.text_result?.text = result
    }

    fun showToastError(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    fun diseablePoint() {
        activity?.button_point?.isClickable = false
    }

    fun activePoint() {
        activity?.button_point?.isClickable = true
    }
}