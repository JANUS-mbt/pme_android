package com.janus.platoon.di.module

import com.janus.platoon.ui.login.MainActivityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivityFragment(): MainActivityFragment
}
