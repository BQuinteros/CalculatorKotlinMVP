package com.example.myapplication.mvp.view.base

import android.app.Activity

import java.lang.ref.WeakReference

open class ActivityView(activity: Activity) {
    private val activityRef: WeakReference<Activity> = WeakReference(activity)

    val activity: Activity?
        get() = activityRef.get()
}