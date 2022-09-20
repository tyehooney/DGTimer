package com.example.dgtimer.utils

import android.content.Context
import android.content.res.Resources
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.isVisible
import java.io.IOException

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

    fun Context.readUpdateNote(versionName: String): String? {
        val rawId =
            resources.getIdentifier(
                "update_note_"+versionName.replace(".","_"),
                "raw",
                packageName
            )
        val byte: ByteArray
        try {
            val inputStream = resources.openRawResource(rawId)
            byte = ByteArray(inputStream.available())
            inputStream.read(byte)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
            return null
        }
        return String(byte)
    }

    fun String.trimAllSpaces(): String =
        filter { !it.isWhitespace() }
}