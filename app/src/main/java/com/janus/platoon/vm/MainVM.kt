package com.janus.platoon.vm

import android.view.View
import com.janus.platoon.base.BaseViewModel
import com.janus.platoon.data.Location
import com.janus.platoon.ui.choose.ChooseActivity
import com.janus.platoon.usecase.VehicleUseCase
import com.janus.platoon.util.Status
import com.janus.platoon.util.startActivityWithName
import javax.inject.Inject


class MainVM
@Inject constructor(val vehicleUseCase: VehicleUseCase) : BaseViewModel() {
    init {
        status.value = Status.IN_PROGRESS
        add(vehicleUseCase.getVehicles().subscribe {
            status.postValue(it)
        })
    }

    fun performPlatoonMe(view: View) {
        view.context.startActivityWithName(ChooseActivity::class.java, false)
    }

    fun getLocations(): List<Location> {
        return vehicleUseCase.getCurrentVehicleInfo()
    }
}
