package com.example.myapplication.mvp.view

import android.app.Activity
import com.example.myapplication.mvp.view.base.ActivityView
import kotlinx.android.synthetic.main.activity_main.text_result
import kotlinx.android.synthetic.main.activity_main.text_visor

class CalculatorView(activity: Activity) : ActivityView(activity){

    fun setVisor(operation : String){
        activity!!.text_visor.text = operation
    }

    fun setResult(result : String){
        activity!!.text_result.text = result
    }
}