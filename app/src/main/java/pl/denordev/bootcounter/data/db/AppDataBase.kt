package pl.denordev.bootcounter.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.denordev.bootcounter.data.model.BootEventDto

@Database(
    entities = [BootEventDto::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun eventsDao(): BootEventsDao
}