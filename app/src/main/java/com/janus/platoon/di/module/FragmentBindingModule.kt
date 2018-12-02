package com.janus.platoon.di.module

import com.janus.platoon.ui.choose.ChooseActivityFragment
import com.janus.platoon.ui.main.MainActivityFragment
import com.janus.platoon.ui.platoon.PlatoonActivityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivityFragment(): MainActivityFragment

    @ContributesAndroidInjector
    internal abstract fun contributeChooseActivityFragment(): ChooseActivityFragment

    @ContributesAndroidInjector
    internal abstract fun contributePlatoonActivityFragment(): PlatoonActivityFragment
}
