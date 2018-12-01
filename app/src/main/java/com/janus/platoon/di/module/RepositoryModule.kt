package com.janus.platoon.di.module

import android.content.SharedPreferences
import com.janus.platoon.di.scope.AppScope
import com.janus.platoon.repo.TokenRepository
import dagger.Module
import dagger.Provides

@Module(includes = [SharedPrefUtilModule::class])
class RepositoryModule {

    @AppScope
    @Provides
    internal fun provideTokenRepository(sharedPreferences: SharedPreferences): TokenRepository {
        return TokenRepository(sharedPreferences)
    }
}
