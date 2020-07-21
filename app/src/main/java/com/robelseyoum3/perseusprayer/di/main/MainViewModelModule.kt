package com.robelseyoum3.perseusprayer.di.main

import androidx.lifecycle.ViewModel
import com.robelseyoum3.perseusprayer.di.ViewModelKey
import com.robelseyoum3.perseusprayer.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel ::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}