package pl.denordev.bootcounter.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.denordev.bootcounter.data.repository.EventsRepositoryImpl
import pl.denordev.bootcounter.domain.model.BootEvent
import pl.denordev.bootcounter.presentation.utils.formatTimestamp
import javax.inject.Inject

@AndroidEntryPoint
class BootEventsReceiver : BroadcastReceiver() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("EventsReceiver", "Coroutine exception", throwable)
    }

    @Inject
    lateinit var repository: EventsRepositoryImpl

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val timestamp = System.currentTimeMillis()
            val bootEvent = BootEvent(
                timestamp = timestamp,
                date = timestamp.formatTimestamp("dd/MM/yyyy")
            )
            coroutineScope.launch(exceptionHandler) {
                repository.saveBootEvent(bootEvent)
            }
        }
    }
}