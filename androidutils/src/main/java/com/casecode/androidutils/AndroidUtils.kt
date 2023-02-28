package com.casecode.androidutils

import android.Manifest
import android.app.Activity
import android.content.*
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Paint
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Android utils
 *
 *
 * Responsible for functions that serve the application
 *
 * @author Abdelaziz Daoud
 * @version 1.0
 * @since 2022-07-25
 */
object AndroidUtils {
    private const val REQUEST_CALL = 1

    /**
     * Font size for table
     *
     * @param paint     paint for table in pdf
     * @param length    length text
     * @param limit     limit text
     * @param textSize1 text size 1
     * @param textSize2 text size 2
     */
    @JvmStatic
    fun fontSize(paint: Paint, length: String, limit: Int, textSize1: Float, textSize2: Float) {
        if (length.length > limit) paint.textSize = textSize1 else paint.textSize = textSize2
    }

    /**
     * Remove duplicates
     *
     * @param <T>  create a new ArrayList
     * @param list list data for remove
     * @return the new list
    </T> */
    fun <T> removeDuplicates(list: ArrayList<T>): ArrayList<T> {
        // Create a new ArrayList
        val newList = ArrayList<T>()

        // Traverse through the first list
        for (element in list) {
            /*
             * If this element is not present in newList
             * then add i
             * */
            if (!newList.contains(element)) {
                newList.add(element)
            }
        }
        return newList
    }

    /**
     * Open PDF
     *
     *
     * Checking if the file exists or not:
     * exists: starting the pdf viewer
     * not: he file not exists!
     *
     * @param activity  activity for class
     * @param parent    parent path
     * @param child     child path
     * @param uriString data url
     */
    fun openPdf(activity: Activity, parent: String?, child: String?, uriString: String?) {
        val pdfFile = File(parent, child!!)
        val data = Uri.parse(uriString)
        val type = "application/pdf"

        // Checking if the file exists or not
        if (pdfFile.exists()) {
            val objIntent = Intent(Intent.ACTION_VIEW)
                .setDataAndType(data, type)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            // Starting the pdf viewer
            activity.startActivity(objIntent)
        } else {
            Toast.makeText(activity, "The file not exists", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Is online with internet
     *
     * @param context context
     * @return result is connected or no
     */
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    /**
     * Get version name
     *
     * @param activity the activity
     * @return version name
     */
    fun getVersionName(activity: Activity): String {
        var version = "0.0"
        try {
            val packageManager = activity.packageManager
            val packageInfo = packageManager.getPackageInfo(activity.packageName, 0)
            version = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return version
    }

    /**
     * Navigate activity.
     *
     * @param c        to avtivate
     * @param button   button of module
     * @param activity activity this
     */
    fun navigateActivity(c: Class<*>?, button: Button, activity: Activity) {
        button.setOnClickListener { v: View? ->
            val intent = Intent(activity, c)
            activity.startActivity(intent)
        }
    }

    /**
     * Install APK
     *
     * @param activity             curretnt
     * @param versionDataarrayList the version dataarray list
     */
    fun installApk(activity: Activity, versionDataList: List<String>) {
        val m = activity.packageManager
        var packageName = activity.packageName
        var p: PackageInfo? = null
        try {
            p = m.getPackageInfo(packageName!!, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        assert(p != null)
        packageName = p!!.applicationInfo.dataDir
        val toInstall = File(
            Environment.getExternalStorageDirectory()
                .toString() + File.separator + versionDataList[1]
        )
        val intent: Intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val apkUri = FileProvider.getUriForFile(
                activity,
                "com.example.connecttosoapapiapp.fileprovider",
                toInstall
            )
            intent = Intent(Intent.ACTION_INSTALL_PACKAGE)
            intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, false)
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            intent.data = apkUri
        } else {
            val apkUri = Uri.fromFile(toInstall)
            intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        activity.startActivity(intent)
    }

    /**
     * Get index spinner
     *
     * @param spinner  spinner
     * @param myString title
     * @return index of spinner
     */
    fun getIndexSpinner(spinner: Spinner, myString: String): Int {
        var index = 0
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i) == myString) {
                index = i
            }
        }
        return index
    }

    /**
     * The view used to make the snackbar.
     *
     *
     * This should be contained within the view hierarchy you want to display the snackbar.
     *
     *
     * Generally it can be the view that was interacted with to trigger the snackbar,
     * such as a button that was clicked, or a card that was swiped.
     *
     * @param view  view
     * @param text  text
     * @param error error: true or false
     */
    @JvmStatic
    fun showSnackbar(view: View, text: CharSequence?, error: Boolean) {
        if (error) {
            var venetianRedColor = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                venetianRedColor = view.context.getColor(R.color.venetian_red)

            Snackbar.make(view, text!!, Snackbar.LENGTH_LONG).setBackgroundTint(venetianRedColor)
                .show()
        } else
            Snackbar.make(view, text!!, Snackbar.LENGTH_SHORT).show()

    }

    /**
     * Hide keyboard
     *
     * @param activity activity
     */
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Show message with dialog
     *
     * @param context for activity
     * @param title   title
     * @param message message
     */
    fun showMessageDialog(context: Context?, title: String?, message: String?) {
        MaterialAlertDialogBuilder(context!!)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.all_okay) { _: DialogInterface?, _: Int -> }
            .show()
    }

    /**
     * Custom format
     *
     *
     * Value: 123456.789, ###,###.###, 123,456.789
     *
     *
     * Value: 123456.789, ###.##, 123456.79
     *
     *
     * Value: 123.78, 000000.000, 000123.780
     *
     *
     * Value: 12345.67, $###,###.###, $12,345.67
     *
     *
     * link: https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
     *
     * @param pattern pattern
     * @param value   value
     * @return format value
     */
    @JvmStatic
    fun customFormat(pattern: String?, value: Double): String {
        return DecimalFormat(pattern).format(value)
    }

    /**
     * Copy text
     *
     * @param context for activity
     * @param text    text to copy
     */
    @JvmStatic
    fun copyText(context: Context, text: String?) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(text, text)
        clipboard.setPrimaryClip(clip)
    }

    /**
     * Mack phone call
     *
     *
     * uses-permission android:name="android.permission.CALL_PHONE"
     *
     * @param context     the context
     * @param phoneNumber the phone number
     * @param activity    the activity
     */
    fun mackPhoneCall(context: Context?, phoneNumber: String, activity: Activity) {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                (context as Activity?)!!,
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CALL
            )
            return
        } else {
            val dial = "tel:$phoneNumber"
            activity.startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
        }
    }

    /**
     * Show material date picker
     *
     * @return material date builder
     */
    fun showMaterialDatePicker(): MaterialDatePicker<Long> {
        /*
         now create instance of the material date picker
         builder make sure to add the "datePicker" which
         is normal material date picker which is the first
         type of the date picker in material design date
         picker
        */
        val materialDateBuilder = MaterialDatePicker.Builder.datePicker()

        // now define the properties of the
        // materialDateBuilder that is title text as SELECT A DATE
        materialDateBuilder.setTitleText("Make another appointment")
        return materialDateBuilder.build()
    }
}