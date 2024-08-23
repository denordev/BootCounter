package pl.denordev.bootcounter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.denordev.bootcounter.domain.model.BootEvent
import pl.denordev.bootcounter.domain.repository.BootEventsRepository
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

data class MainScreenState(
    val isLoading: Boolean = false,
    val bootEvents: List<BootEvent> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bootEventsRepository: BootEventsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    fun loadBootEvents() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val events = bootEventsRepository.getBootEvents()
                _uiState.update { state ->
                    state.copy(bootEvents = events, isLoading = false)
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                _uiState.update { state ->
                    state.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

}