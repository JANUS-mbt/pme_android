package com.janus.platoon.di.module

import com.janus.platoon.di.scope.AppScope
import com.janus.platoon.remote.NetworkHandler
import com.janus.platoon.repo.TokenRepository
import com.janus.platoon.repo.VehicleRepository
import com.janus.platoon.usecase.LoginUseCase
import com.janus.platoon.usecase.VehicleUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class, NetworkModule::class])
class UsecaseModule {
    @AppScope
    @Provides
    internal fun provideLoginUseCase(
        networkHandler: NetworkHandler,
        tokenRepository: TokenRepository
    ): LoginUseCase {
        return LoginUseCase(networkHandler, tokenRepository)
    }

    @AppScope
    @Provides
    internal fun provideVehicleUseCase(
        networkHandler: NetworkHandler,
        tokenRepository: VehicleRepository
    ): VehicleUseCase {
        return VehicleUseCase(networkHandler, tokenRepository)
    }
}
