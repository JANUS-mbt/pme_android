package com.janus.platoon.usecase

import com.janus.platoon.remote.NetworkHandler
import com.janus.platoon.repo.TokenRepository
import com.janus.platoon.util.Status
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    val networkHandler: NetworkHandler,
    val tokenRepository: TokenRepository
) {
    val status = PublishSubject.create<Status>()

    private fun login() {
        networkHandler
            .login("")
            .map(tokenRepository::saveToken)
            .doOnNext(::updateStatus)
            .doOnError { it -> updateStatus(Status.FAILED.withReason(it.message)) }
            .subscribe()
    }

    private fun updateStatus(status: Status) {
        this.status.onNext(status)
    }
}