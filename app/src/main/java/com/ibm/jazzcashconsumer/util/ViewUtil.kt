package com.app.digitalgifting

import android.content.res.Resources

object ViewUtil {
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

}