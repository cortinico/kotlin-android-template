package com.ncorti.kotlin.template.library.android

import android.content.Context
import android.widget.Toast

object ToastUtil {

    fun showToast(context: Context, message: String): Toast =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).also {
            it.show()
        }
}
