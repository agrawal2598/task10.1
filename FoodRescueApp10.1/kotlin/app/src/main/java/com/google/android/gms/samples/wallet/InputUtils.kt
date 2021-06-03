package com.google.android.gms.samples.wallet

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


object InputUtils {

    fun showSoftKeyboard(context: Context, editText: EditText) {
        editText.requestFocus()
        val mgr = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }


    fun hideSoftKeyboard(activity: Activity) {
        val view = activity.findViewById<View>(android.R.id.content)
        val mgr = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(view.windowToken, 0)
    }
}