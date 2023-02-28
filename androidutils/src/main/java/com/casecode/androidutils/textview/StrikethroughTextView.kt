package com.casecode.androidutils.textview

import android.graphics.Paint
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.widget.TextView


object StrikethroughTextView {

    /**
     * Strikethrough the entire text
     */
    fun strikethroughTextView(
        sampleText: String,
        textView: TextView
    ) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        textView.text = sampleText
    }

    /**
     * Strikethrough only parts of the text
     */
    fun strikethroughTextView(
        sampleText: String,
        start: Int,
        end: Int,
        textView: TextView
    ) {
        val spanBuilder = SpannableStringBuilder(sampleText)
        val strikethroughSpan = StrikethroughSpan()
        spanBuilder.setSpan(
            strikethroughSpan,  // Span to add
            start,  // Start
            end,  // End of the span (exclusive)
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE // Text changes will not reflect in the strike changing
        )
        textView.text = spanBuilder
    }
}