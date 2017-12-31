package utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hanjoong on 2017-12-31.
 */

class DateUtils {
    companion object {
        const val SIMPLE_DATE_FORMAT_FULL = SimpleDateFormat.FULL
        fun getDateStringFromTimeMillis(timeMillis: Long) = getDateStringFromTimeMillis(timeMillis, SIMPLE_DATE_FORMAT_FULL)
        
        fun getDateStringFromTimeMillis(timeMillis: Long, dateFormat: Int): String {
            val date = Date(timeMillis)
            val dateFormat = SimpleDateFormat.getDateInstance(dateFormat, Locale.getDefault())
            return dateFormat.format(date)
        }
    }
}