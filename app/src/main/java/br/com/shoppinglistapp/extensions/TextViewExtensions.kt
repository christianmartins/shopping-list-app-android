package br.com.shoppinglistapp.extensions

import android.graphics.Paint
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun TextView.changeColor(@ColorRes resColor: Int){
    setTextColor(ContextCompat.getColor(context, resColor))
}

fun TextView.addPaintFlagsStrikeThroughEffect(){
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}
fun TextView.setPaintFlagsStrikeThroughEffect(isStrikeThrough: Boolean){
    if(isStrikeThrough){
        addPaintFlagsStrikeThroughEffect()
    }else{
        paintFlags = 0
    }
}

