package com.casecode.androidutils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Date utils
 *
 * @author Abdelaziz Daoud
 * @since 2022-02-16
 */
object DateUtils {

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     * Other format: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     *
     * @param date date
     * @return format date
     */
    @JvmStatic
    fun formatDate(date: Date?): String {
        val dateFormat = SimpleDateFormat("LLL dd, yyyy", Locale.ENGLISH)
        return dateFormat.format(date!!)
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     *
     * @param date date
     * @return format time
     */
    @JvmStatic
    fun formatTime(date: Date?): String {
        val timeFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
        return timeFormat.format(date!!)
    }

    /**
     * Format date with string.
     *
     * @param date date
     * @return date
     * @throws ParseException message error
     */
    @JvmStatic
    @Throws(ParseException::class)
    fun formatDateWithString(date: String?): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd h:m:s", Locale.ENGLISH)
        return formatter.parse(date!!)!!
    }

    /**
     * Get current date with format: dd-MM-yyyy
     *
     * @param pattern the pattern
     * @return current date
     */
    @JvmStatic
    fun currentDate(pattern: String?): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(Date())
    }

    /**
     * Get current time with format: HH:mm:ss
     *
     * @return current time
     */
    @JvmStatic
    fun currentTime(): String {
        return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    }

    /**
     * Gets next date.
     *
     * @param currentDate the current date
     * @return the next date
     * @throws ParseException the parse exception
     */
    @JvmStatic
    @Throws(ParseException::class)
    fun getNextDate(currentDate: String?): String {
        val format = SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.ENGLISH)
        val calendar = Calendar.getInstance()
        calendar.time = format.parse(currentDate!!)!!
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        return format.format(calendar.time)
    }
}