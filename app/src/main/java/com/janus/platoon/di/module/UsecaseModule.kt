package com.janus.platoon.di.module

import com.janus.platoon.di.scope.AppScope
import com.janus.platoon.remote.NetworkHandler
import com.janus.platoon.repo.TokenRepository
import com.janus.platoon.usecase.LoginUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class, NetworkModule::class])
class UsecaseModule {
    @AppScope
    @Provides
    internal fun provideFacebookLoginUseCase(
        networkHandler: NetworkHandler,
        tokenRepository: TokenRepository
    ): LoginUseCase {
        return LoginUseCase(networkHandler, tokenRepository)
    }
}
