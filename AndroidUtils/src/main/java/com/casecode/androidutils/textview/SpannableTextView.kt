package com.casecode.androidutils.textview

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.widget.TextView

/**
 * The type Spannable text view.
 *
 * @author Abdelaziz Daoud
 * @version 1.0
 * @since 2022-11-23
 */
object SpannableTextView {

    /**
     * In order to set a different color to some portion of the text
     *
     * @param firstWord
     * @param lastWord
     * @param firstWordColor
     * @param lastWordColor
     * @param textView
     */
    fun spannableColor(
        firstWord: String,
        lastWord: String,
        firstWordColor: Int,
        lastWordColor: Int,
        textView: TextView
    ) {
        val spannable: Spannable = SpannableString(firstWord + lastWord)

        spannable.setSpan(
            ForegroundColorSpan(firstWordColor),
            0,
            firstWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(lastWordColor),
            firstWord.length,
            firstWord.length + lastWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = spannable
    }

    /**
     * In order to set a different font size for some portions of the text
     *
     * @param firstWord
     * @param lastWord
     * @param textView
     */
    fun spannableFont(
        firstWord: String,
        lastWord: String,
        textView: TextView
    ) {
        val spannable: Spannable = SpannableString(firstWord + lastWord)
        spannable.setSpan(
            RelativeSizeSpan(1.1f), 0, firstWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        ) // set size

        spannable.setSpan(
            RelativeSizeSpan(0.8f), firstWord.length, firstWord.length +
                    lastWord.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        ) // set size

        textView.text = spannable
    }

    /**
     * In order to set a different font typeface to some portion of the text
     *
     * @param firstWord
     * @param lastWord
     * @param fontBold
     * @param fontRegular
     * @param textView
     */
    fun spannableTypeface(
        firstWord: String,
        lastWord: String,
        fontBold: Typeface,
        fontRegular: Typeface,
        textView: TextView
    ) {
        val spannable: Spannable = SpannableString(firstWord + lastWord)
        spannable.setSpan(
            CustomTypefaceSpan("SFUIText-Bold.otf", fontBold), 0,
            firstWord.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            CustomTypefaceSpan("SFUIText-Regular.otf", fontRegular),
            firstWord.length, firstWord.length + lastWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannable
    }

    /**
     * Make text-align to top
     */
    fun topAlignSuperscriptSpan(
        source: String,
        start: Int,
        end: Int,
        textView: TextView
    ) {
        val spannableString = SpannableString(source)
        spannableString.setSpan(
            TopAlignSuperscriptSpan(0.35.toFloat()), start, end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannableString
    }

    /**
     * You can archive different Textsizes inside a Textview with a Span
     */
    fun textviewWithDifferentTextSize(
        textView: TextView,
        start: Int,
        end: Int
    ) {
        val span: Spannable = SpannableString(textView.text)
        span.setSpan(RelativeSizeSpan(0.8f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = span
    }

    /**
     * Create colored text
     * * String name = getColoredSpanned("Hiren", "#800000");
     * * String surName = getColoredSpanned("Patel","#000080");
     * * txtView.setText(Html.fromHtml(name+" "+surName));
     */
    fun getColoredSpanned(text: String, color: String): String? {
        return "<font color=$color>$text</font>"
    }

    /**
     * <h2>Spannable color</h2>
     *
     *
     * In order to set a different color to some portion of text,
     * a ForegroundColorSpan can be used.
     *
     * @param textView       text view
     * @param firstWord      first word
     * @param lastWord       last word
     * @param firstWordColor first word color
     * @param lastWordColor  last word color
     * @param twoLines       the two lines
     */
    @JvmStatic
    fun spannableColor(
        textView: TextView,
        firstWord: String,
        lastWord: String,
        firstWordColor: Int,
        lastWordColor: Int,
        twoLines: Boolean
    ) {
        val spannable: Spannable = if (twoLines) SpannableString(
            """
    $firstWord
    $lastWord
    """.trimIndent()
        ) else SpannableString(firstWord + lastWord)
        spannable.setSpan(
            ForegroundColorSpan(firstWordColor),
            0,
            firstWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(lastWordColor),
            firstWord.length,
            firstWord.length + lastWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannable
    }
}