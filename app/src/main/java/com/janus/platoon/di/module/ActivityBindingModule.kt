package com.janus.platoon.di.module

import com.janus.platoon.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}
