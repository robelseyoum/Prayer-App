package com.robelseyoum3.perseusprayer.di.main

import androidx.lifecycle.ViewModel
import com.robelseyoum3.perseusprayer.di.ViewModelKey
import com.robelseyoum3.perseusprayer.ui.main.manageplaces.ManagePlacesViewModel
import com.robelseyoum3.perseusprayer.ui.main.prayertimes.PrayerTimesViewModel
import com.robelseyoum3.perseusprayer.ui.main.qibla.QiblaViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ManagePlacesViewModel ::class)
    abstract fun bindManagePlacesViewModel(managePlacesViewModel: ManagePlacesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PrayerTimesViewModel ::class)
    abstract fun bindPrayerTimesViewModel(prayerTimesViewModel: PrayerTimesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QiblaViewModel ::class)
    abstract fun bindQiblaViewModel(qiblaViewModel: QiblaViewModel): ViewModel
}