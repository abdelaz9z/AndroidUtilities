package com.casecode.androidutils

import android.app.Activity
import android.widget.TextView
import java.text.NumberFormat

/**
 * Number utils
 *
 * @author Abdelaziz Daoud
 * @since 2023-05-29
 */
object NumberUtils {

    /**
     * Format the tip amount according to the local currency and display it onscreen.
     * Example would be "Tip Amount: $10.00".
     *
     * @param number number or amount
     * @param textView text view
     * @param resId tip Amount: %s
     */
    private fun numberFormatDollarSign(
        number: Double,
        textView: TextView,
        resId: Int,
        activity: Activity
    ) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(number)
        textView.text = activity.getString(resId, formattedTip)
    }
}