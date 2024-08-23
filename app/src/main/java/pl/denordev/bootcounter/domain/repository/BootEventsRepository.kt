package pl.denordev.bootcounter.domain.repository

import pl.denordev.bootcounter.domain.model.BootEvent

interface BootEventsRepository {

    suspend fun getBootEvents(): List<BootEvent>

    suspend fun saveBootEvent(bootEvent: BootEvent)
}