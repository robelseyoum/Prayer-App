package com.robelseyoum3.perseusprayer.di

import android.app.Application
import com.robelseyoum3.perseusprayer.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
            AndroidInjectionModule::class,
            AppModule::class,
            ActivityBuildersModule::class,
            ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {


    @Component.Builder
    interface Builder {
        
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}