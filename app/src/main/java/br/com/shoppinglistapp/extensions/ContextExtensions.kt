package br.com.shoppinglistapp.extensions

import android.content.Context

fun Context.dpFromPx(px: Int): Int {
    return (px / resources.displayMetrics.density).toInt()
}

fun Context.pxFromDp(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}