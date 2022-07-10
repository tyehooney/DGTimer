package com.example.dgtimer.utils

import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.isVisible

object Extensions {
    fun EditText.setSearchFocus(focus: Boolean) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (focus) {
            isVisible = true
            requestFocus()
            imm.showSoftInput(this, 0)
        } else {
            setText("")
            clearFocus()
            isVisible = false
            imm.hideSoftInputFromWindow(windowToken, 0)
        }

        setOnEditorActionListener { _, editorInfo, _ ->
            if (editorInfo == EditorInfo.IME_ACTION_SEARCH) {
                clearFocus()
                imm.hideSoftInputFromWindow(windowToken, 0)
            }
            setOnEditorActionListener(null)
            true
        }
    }
}