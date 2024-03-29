package com.example.dgtimer.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Build
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dgtimer.db.CapsuleType
import java.io.IOException

internal fun EditText.setSearchFocus(focus: Boolean) {
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

internal fun <T, VH : ViewHolder> ListAdapter<T, VH>.updateListWithSavingState(
    recyclerView: RecyclerView,
    newList: List<T>?
) {
    val saveState = recyclerView.layoutManager?.onSaveInstanceState()
    submitList(newList) {
        recyclerView.layoutManager?.onRestoreInstanceState(saveState)
    }
}

@SuppressLint("DiscouragedApi")
internal fun Context.readUpdateNote(versionName: String): String? {
    val rawId =
        resources.getIdentifier(
            "update_note_" + versionName.replace(".", "_"),
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

internal fun String.trimAllSpaces(): String =
    filter { !it.isWhitespace() }

internal fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
    }

internal fun Int.getCapsuleType(): CapsuleType =
    CapsuleType.values().find { it.id == this } ?: CapsuleType.COFFEE