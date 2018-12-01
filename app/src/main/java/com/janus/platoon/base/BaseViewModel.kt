package com.janus.platoon.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    val TAG = javaClass.simpleName

    private val disposables = CompositeDisposable()

    fun add(d: Disposable) {
        disposables.add(d)
    }

    override fun onCleared() {
        dispose()
        super.onCleared()
    }

    fun dispose() {
        disposables.clear()
    }
}