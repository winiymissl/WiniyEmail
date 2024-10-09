package com.example.winiychat.utils

import android.content.Context

class PxADPUtils(context: Context) {
    private val density = context.resources.displayMetrics.density

    fun dpToPx(dp: Float): Int {
        return (dp * density + 0.5f).toInt()
    }

    fun pxToDp(px: Float): Int {
        return (px / density + 0.5f).toInt()
    }
}