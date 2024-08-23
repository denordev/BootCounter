package pl.denordev.bootcounter.data.repository

import pl.denordev.bootcounter.data.db.BootEventsDao
import pl.denordev.bootcounter.data.model.toDomainModel
import pl.denordev.bootcounter.data.model.toDto
import pl.denordev.bootcounter.domain.model.BootEvent
import pl.denordev.bootcounter.domain.repository.BootEventsRepository
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val dao: BootEventsDao
) : BootEventsRepository {
    override suspend fun getBootEvents(): List<BootEvent> {
        return dao.getAll().map { it.toDomainModel() }
    }

    override suspend fun saveBootEvent(bootEvent: BootEvent) {
        dao.insert(bootEvent.toDto())
    }
}