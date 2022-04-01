package com.eargaez.mvvm_clean.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorText")
fun errorText(view: View, error: String?) {
    when (view) {
        is TextInputLayout -> {
            view.error = error
        }
    }
}