package com.casecode.androidutils

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * The type Reg exp.
 *
 * @author Abdelaziz Daoud
 * @version 1.0
 * @since 2022-11-23
 */
object RegExpUtils {

    /**
     * Get latitude and longitude from texts
     *
     * @param data data text
     * @return latitude and longitude
     */
    @JvmStatic
    fun getLongAndLat(data: String?): String? {
        val pattern = Pattern.compile("([+-]?\\d+\\.\\d+)\\s*,\\s*([+-]?\\d+\\.\\d+)")
        val matcher = pattern.matcher(data!!)

        return if (matcher.find()) {
            matcher.group(1)!! + "," + matcher.group(2)
        } else {
            null
        }
    }

    /**
     * Regular expression 'tracking number'
     *
     * @param trackingNumber tracking number
     * @return result true or false
     */
    fun regularExpressionTrackingNumber(trackingNumber: String?): Boolean {
        val pattern = Pattern.compile("[a-z ]")
        val trackingNumberMatcher = pattern.matcher(trackingNumber!!)
        return !trackingNumberMatcher.find()
    }

    /**
     * Check email
     *
     * @param email the email
     * @return check email
     */
    fun checkEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)"
        val emailAddressPattern: Pattern = Pattern.compile(emailPattern)
        return emailAddressPattern.matcher(email).matches()
    }

    /**
     * Passwords must contain:
     *
     * * password must be at least 10 characters long
     * * a minimum of 1 numeric character [0-9]
     * * a minimum of 1 lower case letter [a-z]
     * * a minimum of 1 upper case letter [A-Z]
     * * a minimum of 1 special character: [@#$%^&+=]
     *
     * @param password the password
     * @return is valid password
     */
    fun isValidPassword(password: String?): Boolean {

        val passwordPattern = "^" +  //# start-of-string
                "(?=.*\\d)" +      //# a digit must occur at least once
                "(?=.*[a-z])" +      //# a lower case letter must occur at least once
                "(?=.*[A-Z])" +      //# an upper case letter must occur at least once
                "(?=.*[@#$%^&+=])" + //# a special character must occur at least once you can replace with your special characters
                "(?=\\S+$)" +        //# no whitespace allowed in the entire string
                ".{10,}" +            //# anything, at least six places though
                "$"                  //# end-of-string

        val pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password!!)
        return matcher.matches()
    }
}