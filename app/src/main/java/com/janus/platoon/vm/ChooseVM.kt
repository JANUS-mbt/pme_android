package com.janus.platoon.vm

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.janus.platoon.base.BaseViewModel
import com.janus.platoon.util.startNavigation
import javax.inject.Inject


class ChooseVM
@Inject constructor() : BaseViewModel() {
    val position=MutableLiveData<LatLng>()
    fun setSelectedMarker(marker: Marker?) {
        position.postValue(marker!!.position)
    }

    fun performRoute(view: View) {
        view.context!!.startNavigation(position.value!!)
    }
}
