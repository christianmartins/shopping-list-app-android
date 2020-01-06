package br.com.shoppinglistapp.utils

import java.text.SimpleDateFormat
import java.util.*


object DateUtils {

    fun getDateTime(): Long{
        val cal = Calendar.getInstance(Locale.getDefault())
        val dateFormat = SimpleDateFormat("ddMMyyyyHHmm", Locale.getDefault())
        return dateFormat.format(cal.time).toLong()
    }

    fun getFormatDateTime(time: Long): String {
        val currentDateFormat = SimpleDateFormat("ddMMyyyyHHmm", Locale.getDefault())
        val dateInString = time.toString()
        val date = currentDateFormat.parse(dateInString)
        return date?.let {
            val format = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
            format.format(date)
        }?: ""
    }

    fun getTimeStamp() = System.currentTimeMillis()
}