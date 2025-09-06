package com.tyehooney.domain.model

import java.util.Locale

interface GlobalString {
    val localString: String
}

data class NameInfo(
    val default: String = "",
    val kr: String = ""
) : GlobalString {
    override val localString: String
        get() = when (Locale.getDefault()) {
            Locale.KOREA, Locale.KOREAN -> kr
            else -> default
        }
}