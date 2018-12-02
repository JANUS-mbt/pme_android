package com.janus.platoon.remote

import com.janus.platoon.data.OAuthResponse
import com.janus.platoon.data.Vehicle
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NetworkHandler
@Inject constructor(var api: NetworkService, networkHandlerHolder: NetworkHandlerHolder) {

    init {
        networkHandlerHolder.networkHandler = this
    }

    //region non authorized
    fun login(accessToken: String): Flowable<DataHolder<OAuthResponse>> {
        return sendRequest(api.facebookLogin(accessToken))
    }

    fun refreshToken(): Flowable<DataHolder<OAuthResponse>> {
        return sendRequest(api.refreshToken())
    }
    //endregion

    //region authorized

    fun getVehicles(): Flowable<DataHolder<List<Vehicle>>> {
        return sendRequest(api.getVehicles())
    }
    //endregion



    private fun <T> sendRequest(call: Flowable<T>): Flowable<DataHolder<T>> {
        return call
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map<DataHolder<T>> { data -> DataHolder.Success(data) }
            .onErrorResumeNext { throwable: Throwable ->
                Flowable.just(
                    DataHolder.Error<T>(throwable)
                ) as Flowable<out DataHolder<T>>
            }
            .doOnError { t: Throwable -> t.printStackTrace() }
    }

}
