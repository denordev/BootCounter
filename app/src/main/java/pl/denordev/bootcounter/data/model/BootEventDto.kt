package pl.denordev.bootcounter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.denordev.bootcounter.data.db.BootEventsDao
import pl.denordev.bootcounter.domain.model.BootEvent

@Entity
data class BootEventDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "date") val date: String
)

fun BootEventDto.toDomainModel() = BootEvent(
    id = id,
    timestamp = timestamp,
    date = date
)

fun BootEvent.toDto() = BootEventDto(
    id = id,
    timestamp = timestamp,
    date = date
)
