package com.robelseyoum3.perseusprayer.di

import com.robelseyoum3.perseusprayer.di.main.MainFragmentBuildersModule
import com.robelseyoum3.perseusprayer.di.main.MainModule
import com.robelseyoum3.perseusprayer.di.main.MainScope
import com.robelseyoum3.perseusprayer.di.main.MainViewModelModule
import com.robelseyoum3.perseusprayer.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainModule::class, MainFragmentBuildersModule::class, MainViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}