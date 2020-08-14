package com.robelseyoum3.perseusprayer.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.persistence.AppDatabase
import com.robelseyoum3.perseusprayer.data.persistence.AppDatabase.Companion.DATABASE_NAME
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.persistence.PrayerTimesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {


    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if scheme changed
            .build()
    }

    @Singleton
    @Provides
    fun providePrayerMethodsDao(db: AppDatabase): PrayerMethodsDao {
        return db.getPrayerMethodsDao()
    }

    @Singleton
    @Provides
    fun providePrayerTimesDao(db: AppDatabase): PrayerTimesDao {
        return db.getPrayerTimesDao()
    }

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.prayerbackground)
            .error(R.drawable.prayerbackground)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideLastLocation(context: Context): FusedLocationProviderClient {
       return LocationServices.getFusedLocationProviderClient(context)
    }
}