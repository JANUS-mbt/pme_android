package com.janus.platoon.vm

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.janus.platoon.base.BaseViewModel
import com.janus.platoon.data.Location
import com.janus.platoon.ui.platoon.PlatoonActivity
import com.janus.platoon.usecase.VehicleUseCase
import com.janus.platoon.util.startActivityWithName
import com.janus.platoon.util.startNavigation
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ChooseVM
@Inject constructor(vehicleUseCase: VehicleUseCase) : BaseViewModel() {
    val position = MutableLiveData<LatLng>()
    val vehicles = MutableLiveData<List<Location>>()

    init {
        vehicles.value = vehicleUseCase.getLocations()
    }

    fun setSelectedMarker(marker: Marker?) {
        position.postValue(if (marker != null) marker.position else null)
    }


    fun performRoute(view: View) {
        view.context!!.startNavigation(position.value!!)
        add(Observable.timer(15, TimeUnit.SECONDS).subscribe {
            view.context!!.startActivityWithName(PlatoonActivity::class.java)
        })
    }
}
