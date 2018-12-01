package com.janus.platoon.vm

import android.view.View
import com.janus.platoon.base.BaseViewModel
import com.janus.platoon.ui.choose.ChooseActivity
import com.janus.platoon.util.Status
import com.janus.platoon.util.startActivityWithName
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainVM
@Inject constructor() : BaseViewModel() {
    fun performPlatoonMe(view: View) {
        status.postValue(Status.IN_PROGRESS)
        //todo replace it with request
        add(Observable.timer(2, TimeUnit.SECONDS).subscribe {
            view.context.startActivityWithName(ChooseActivity::class.java, false)
            status.postValue(Status.NOT_STARTED)
        })
    }
}
