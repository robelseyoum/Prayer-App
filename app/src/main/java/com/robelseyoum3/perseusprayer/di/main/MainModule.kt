package com.robelseyoum3.perseusprayer.di.main


import android.content.SharedPreferences
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.persistence.PrayerTimesDao
import com.robelseyoum3.perseusprayer.data.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule {


    @MainScope
    @Provides
    fun provideMainRepository(
        prayerTimesDao: PrayerTimesDao,
        prayerMethodsDao: PrayerMethodsDao,
        sharedPreferences: SharedPreferences,
        sharedPrefsEditor: SharedPreferences.Editor
    ): MainRepository{
        return MainRepository(
            prayerTimesDao,
            prayerMethodsDao,
            sharedPreferences,
            sharedPrefsEditor
        )
    }





}