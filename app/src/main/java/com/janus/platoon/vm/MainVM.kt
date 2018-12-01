package com.janus.platoon.vm

import android.view.View
import com.janus.platoon.base.BaseViewModel
import com.janus.platoon.ui.choose.ChooseActivity
import com.janus.platoon.util.startActivityWithName
import javax.inject.Inject


class MainVM
@Inject constructor() : BaseViewModel() {
    fun performPlatoonMe(view: View) {
        view.context.startActivityWithName(ChooseActivity::class.java, false)
    }
}
