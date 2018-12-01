package com.janus.platoon.di

import android.app.Application
import com.janus.platoon.App
import com.janus.platoon.di.module.*
import com.janus.platoon.di.scope.AppScope
import dagger.BindsInstance

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, ActivityBindingModule::class, ViewModelModule::class, NetworkModule::class, SharedPrefUtilModule::class, RepositoryModule::class])
@AppScope
internal interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
