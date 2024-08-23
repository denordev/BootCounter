package pl.denordev.bootcounter.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.denordev.bootcounter.R
import pl.denordev.bootcounter.domain.model.BootEvent

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    uiState: MainScreenState
) {
    var dismissalsAllowed by remember { mutableIntStateOf(5) }
    var intervalBetweenDismissals by remember { mutableIntStateOf(15) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.boot_events),
                style = MaterialTheme.typography.bodyMedium
            )

            if (uiState.bootEvents.isEmpty()) {
                Text(text = stringResource(R.string.no_boots_detected))
            } else {
                val groupedEvents = uiState.bootEvents.groupBy { it.date }
                groupedEvents.forEach { bootTime ->
                    Text(text = stringResource(R.string.boot_at, bootTime.key, bootTime.value.size))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = dismissalsAllowed.toString(),
                onValueChange = { dismissalsAllowed = it.toIntOrNull() ?: 5 },
                label = {
                    Text(
                        text = stringResource(R.string.total_dismissals_allowed)
                    )
                }
            )

            TextField(
                value = intervalBetweenDismissals.toString(),
                onValueChange = { intervalBetweenDismissals = it.toIntOrNull() ?: 20 },
                label = {
                    Text(
                        text = stringResource(R.string.interval_between_dismissals_minutes)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        uiState = MainScreenState(
            bootEvents = listOf(
                BootEvent(
                    timestamp = System.currentTimeMillis(),
                    date = "01/04/2024"
                ),
                BootEvent(
                    timestamp = System.currentTimeMillis(),
                    date = "01/04/2024"
                ),
                BootEvent(
                    timestamp = System.currentTimeMillis(),
                    date = "02/04/2024"
                ),
                BootEvent(
                    timestamp = System.currentTimeMillis(),
                    date = "02/04/2024"
                ),

                )
        )
    )
}