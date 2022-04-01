package com.eargaez.mvvm_clean.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.eargaez.mvvm_clean.R
import com.eargaez.mvvm_clean.domain.Resource

/**
 * Created by Eduardo on 3/2/21
 */

fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.snackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction(R.string.button_retry) {
            it()
        }
    }
    snackbar.show()
}

fun SwipeRefreshLayout.isRefreshing(isRefreshing: Boolean?) {
    if (isRefreshing != null)
        this.isRefreshing = isRefreshing
    else
        this.isRefreshing = false
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

fun Fragment.handleApiErrors (
    failure: Resource.Failure,
    retry: (() -> Unit)?  = null
) {
    when {
        failure.isNetworkError -> requireView().snackBar("You need a network connection")
        failure.errorCode == 401 -> {
            requireView().snackBar("Your credentials are invalid")
        }
        failure.errorCode == 422 -> {
            requireView().snackBar("Check the data that you're trying to send")
        }
        else -> requireView().snackBar(failure.errorBody ?: "Unknown error")
    }
}