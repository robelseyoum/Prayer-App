package com.robelseyoum3.perseusprayer.di.main

import com.robelseyoum3.perseusprayer.concurrency.AppDispatchers
import com.robelseyoum3.perseusprayer.concurrency.AppDispatchersImpl
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.persistence.PrayerTimesDao
import com.robelseyoum3.perseusprayer.data.repository.IPrayerDatabase
import com.robelseyoum3.perseusprayer.data.repository.IPrayerTime
import com.robelseyoum3.perseusprayer.data.repository.PrayerDatabaseRepo
import com.robelseyoum3.perseusprayer.data.repository.PrayerTimeRepo
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideAppDispatcher(): AppDispatchers {
        return AppDispatchersImpl()
    }

    @MainScope
    @Provides
    fun provideMainRepository(): IPrayerTime {
        return PrayerTimeRepo()
    }

    @MainScope
    @Provides
    fun providePrayerDatabaseRepo(
        prayerTimesDao: PrayerTimesDao,
        prayerMethodsDao: PrayerMethodsDao,
        appDispatchers: AppDispatchers
    ): IPrayerDatabase {
        return PrayerDatabaseRepo(prayerTimesDao, prayerMethodsDao, appDispatchers)
    }

}