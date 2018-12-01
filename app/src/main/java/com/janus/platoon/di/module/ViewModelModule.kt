package com.janus.platoon.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.janus.platoon.base.ViewModelFactory
import com.janus.platoon.di.mapkey.ViewModelKey
import com.janus.platoon.vm.ChooseVM
import com.janus.platoon.vm.MainVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainVM::class)
    internal abstract fun bindMainVM(viewModel: MainVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChooseVM::class)
    internal abstract fun bindChooseVM(viewModel: ChooseVM): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
