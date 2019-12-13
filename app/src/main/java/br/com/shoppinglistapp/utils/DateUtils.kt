package br.com.shoppinglistapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDateTime(): Long{
        val date = Date().time
        val format = SimpleDateFormat("ddMMyyyyHHmm", Locale.getDefault())
        return format.format(date).toLong()
    }

    fun getFormatDateTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return format.format(date)
    }

    fun getTimeStamp() = System.currentTimeMillis()
}