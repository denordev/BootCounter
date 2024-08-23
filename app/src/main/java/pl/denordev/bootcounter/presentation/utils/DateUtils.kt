package pl.denordev.bootcounter.presentation.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun Long.formatTimestamp(pattern: String = "dd/MM/yyyy HH:mm:ss"): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val instant = Instant.ofEpochMilli(this)
        instant.atZone(ZoneId.systemDefault()).format(formatter)
    } else {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        val date = Date(this)
        formatter.format(date)
    }
}