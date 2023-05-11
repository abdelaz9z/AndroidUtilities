package com.casecode.androidutils.textview

import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import kotlin.math.pow
import kotlin.math.sqrt


class PinchzoomOnTextView : View.OnTouchListener {

    private val STEP = 200f
    private var textView: TextView? = null
    private var mRatio = 1.0f
    private var mBaseDist = 0
    private var mBaseRatio = 0f
    var fontSize = 13f

    /*
    * in onCrete:
    *       textView.setTextSize(mRatio + fontSize);
    * */

    fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.pointerCount == 2) {
            val action = event.action
            val pureAction = action and MotionEvent.ACTION_MASK
            if (pureAction == MotionEvent.ACTION_POINTER_DOWN) {
                mBaseDist = getDistance(event)
                mBaseRatio = mRatio
            } else {
                val delta: Float = (getDistance(event) - mBaseDist) / STEP
                val multi = 2.0.pow(delta.toDouble()).toFloat()
                mRatio = 1024.0f.coerceAtMost(0.1f.coerceAtLeast(mBaseRatio * multi))
                textView?.textSize = mRatio + 13
            }
        }
        return true
    }

    private fun getDistance(event: MotionEvent): Int {
        val dx = (event.getX(0) - event.getX(1)).toInt()
        val dy = (event.getY(0) - event.getY(1)).toInt()
        return sqrt((dx * dx + dy * dy).toDouble()).toInt()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return false
    }
}