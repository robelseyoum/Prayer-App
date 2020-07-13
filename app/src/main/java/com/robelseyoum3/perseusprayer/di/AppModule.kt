package com.robelseyoum3.perseusprayer.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.robelseyoum3.perseusprayer.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {






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
}