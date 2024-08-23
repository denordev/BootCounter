package pl.denordev.bootcounter.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.denordev.bootcounter.data.model.BootEventDto

@Dao
interface BootEventsDao {

    @Query("SELECT * FROM BootEventDto")
    suspend fun getAll(): List<BootEventDto>

    @Insert
    suspend fun insert(bootEvent: BootEventDto)

}