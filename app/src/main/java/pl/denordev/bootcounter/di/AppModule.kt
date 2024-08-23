package pl.denordev.bootcounter.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.denordev.bootcounter.data.db.AppDataBase
import pl.denordev.bootcounter.data.db.BootEventsDao
import pl.denordev.bootcounter.data.repository.EventsRepositoryImpl
import pl.denordev.bootcounter.domain.repository.BootEventsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBootEventsRepository(bootEventsDao: BootEventsDao): BootEventsRepository {
        return EventsRepositoryImpl(bootEventsDao)
    }

    @Provides
    fun provideBootEventsDao(appDatabase: AppDataBase): BootEventsDao {
        return appDatabase.eventsDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            context = appContext,
            klass = AppDataBase::class.java,
            name = "events"
        ).build()
    }
}