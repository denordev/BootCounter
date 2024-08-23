package pl.denordev.bootcounter.presentation

import android.text.format.DateUtils.formatDateTime
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.denordev.bootcounter.domain.model.BootEvent
import pl.denordev.bootcounter.domain.repository.BootEventsRepository
import pl.denordev.bootcounter.presentation.utils.formatTimestamp
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.abs

data class MainScreenState(
    val isLoading: Boolean = false,
    val bootEvents: List<BootEvent> = emptyList(),
    val error: String? = null,
    val notificationBody: String? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bootEventsRepository: BootEventsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        loadBootEvents()
    }

    private fun loadBootEvents() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val events = bootEventsRepository.getBootEvents()
                _uiState.update { state ->
                    state.copy(
                        bootEvents = events,
                        isLoading = false,
                        notificationBody = generateNotificationBody(events)
                    )
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                _uiState.update { state ->
                    state.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    private fun generateNotificationBody(bootEvents: List<BootEvent>): String {
        return when (bootEvents.size) {
            0 -> "No boots detected"
            1 -> {
                val dateOfBoot = bootEvents.first().timestamp.formatTimestamp()
                "The boot was detected = $dateOfBoot"
            }
            else -> {
                val lastBoot = bootEvents[0].timestamp
                val secondLastBoot = bootEvents[1].timestamp
                val deltaSeconds = abs((lastBoot - secondLastBoot) / 1000)
                "Last boots time delta = ${deltaSeconds}s"
            }
        }
    }

}