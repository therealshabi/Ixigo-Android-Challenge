package technolifestyle.com.ixigosearch.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

object HelperUtil {

    fun getTime(timestamp: Long): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(Date(timestamp))
    }

    fun getFormattedDuration(timestampStart: Long, timestampEnd: Long): String {
        val startDate = Date(timestampStart)
        val endDate = Date(timestampEnd)
        val diff = abs(endDate.time - startDate.time)
        val diffMinutes: Long = diff / (60 * 1000) % 60
        val diffHours: Long = diff / (60 * 60 * 1000) % 24
        return "${diffHours}h ${diffMinutes}m"
    }

    fun getDuration(timestampStart: Long, timestampEnd: Long): Long {
        val startDate = Date(timestampStart)
        val endDate = Date(timestampEnd)
        return abs(endDate.time - startDate.time)
    }
}