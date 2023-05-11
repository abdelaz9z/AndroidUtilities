package com.casecode.androidutils

import android.app.Activity
import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.casecode.androidutils.AndroidUtils.showSnackbar
import java.util.Objects

/**
 * The type Firebase utils.
 *
 * @author Abdelaziz Daoud
 * @version 1.0
 * @since 2022-11-23
 */
object FirebaseUtils {
    /**
     * Auth sign out
     *
     * @param activity         the activity
     * @param constraintLayout the constraint layout
     */
    fun signOut(
        activity: Activity,
        className: Objects,
        constraintLayout: ConstraintLayout?
    ) {
        GoogleSignIn.getClient(
            activity,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        ).signOut().addOnCompleteListener { task: Task<Void?>? ->
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, className::class.java)
            activity.startActivity(intent)
            activity.finish()
        }.addOnFailureListener { e: Exception ->
            showSnackbar(constraintLayout!!, e.message, true)
        }
    }


    fun logout(activity: Activity, className: Objects) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(activity, className::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

}