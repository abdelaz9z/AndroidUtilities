package com.casecode.androidutils

/**
 * The type Barcode utils.
 *
 * @author Abdelaziz Daoud
 * @version 1.0
 * @since 2022-11-23
 */
object BarcodeUtils {

    /**
     * Calculate check digit for scales
     *
     * @param barcode basic barcode
     * @return result barcode
     */
    fun calculateCheckDigitForScales(barcode: String): String {
        val checkDigit: Int
        val odd =
            barcode.substring(1, 2).toInt() + barcode.substring(3, 4).toInt() + barcode.substring(
                5,
                6
            ).toInt() + barcode.substring(7, 8).toInt() + barcode.substring(9, 10)
                .toInt() + barcode.substring(11, 12).toInt()
        val even =
            barcode.substring(0, 1).toInt() + barcode.substring(2, 3).toInt() + barcode.substring(
                4,
                5
            ).toInt() + barcode.substring(6, 7).toInt() + barcode.substring(8, 9)
                .toInt() + barcode.substring(10, 11).toInt()
        checkDigit = if ((odd * 3 + even) % 10 != 0) 10 - (odd * 3 + even) % 10 else 0
        return barcode + checkDigit
    }

    /**
     * Calculate weight from barcode
     *
     * @param barcode barcode
     * @return weight kg
     */
    fun calculateWeightFromBarcode(barcode: String): Double {
        return barcode.substring(7, 12).toDouble() / 1000
    }

    /**
     * Convert from barcode weight to original
     *
     * @param barcode barcode
     * @return original barcode
     */
    fun convertFromBarcodeWeightToOriginal(barcode: String): String {
        return "${barcode.substring(0, 7)}00000${barcode.substring(barcode.length - 1)}"
    }
}