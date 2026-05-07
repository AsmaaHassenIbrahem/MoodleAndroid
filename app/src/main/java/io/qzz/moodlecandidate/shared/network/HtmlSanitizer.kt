package io.qzz.moodlecandidate.shared.network

import android.text.Html

object HtmlSanitizer {
    fun toPlainText(html: String): String {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
    }
}

