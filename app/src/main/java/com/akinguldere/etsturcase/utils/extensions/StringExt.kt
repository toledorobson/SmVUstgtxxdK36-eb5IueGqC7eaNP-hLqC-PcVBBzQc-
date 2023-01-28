package com.akinguldere.etsturcase.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

val String.dateConvert: String
    get() {
        return if (this.isNotBlank()) {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
            formatter.format(parser.parse(this) ?: "0000-00-00")
        } else this
    }
