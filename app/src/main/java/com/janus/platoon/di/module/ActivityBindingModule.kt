package com.janus.platoon.di.module

import com.janus.platoon.ui.choose.ChooseActivity
import com.janus.platoon.ui.main.MainActivity
import com.janus.platoon.ui.platoon.PlatoonActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    internal abstract fun contributeChooseActivity(): ChooseActivity

    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    internal abstract fun contributePlatoonActivity(): PlatoonActivity
}
