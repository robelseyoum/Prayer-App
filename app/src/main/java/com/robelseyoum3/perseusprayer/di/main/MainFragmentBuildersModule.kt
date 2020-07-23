package com.robelseyoum3.perseusprayer.di.main

import com.robelseyoum3.perseusprayer.ui.main.prayertimes.PrayerMethodsDialog
import com.robelseyoum3.perseusprayer.ui.main.prayertimes.PrayerTimesFragment
import com.robelseyoum3.perseusprayer.ui.main.qibla.QiblaFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeQiblaFragment(): QiblaFragment

    @ContributesAndroidInjector()
    abstract fun contributePrayerTimesFragment(): PrayerTimesFragment

    @ContributesAndroidInjector()
    abstract fun contributePrayerMethodsDialog(): PrayerMethodsDialog
}