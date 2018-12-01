package com.janus.platoon.util

import android.content.Context
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.janus.platoon.R
import com.janus.platoon.data.Location
import com.janus.platoon.data.LocationType

fun GoogleMap.addMarkersAndMoveCamera(context: Context, locations: List<Location>): CameraUpdate? {
    val latLngBoundsbuilder = LatLngBounds.Builder()
    locations.forEach {
        val latLng = LatLng(it.lat, it.lon)
        latLngBoundsbuilder.include(latLng)
        val icon = context.bitmapDescriptorFromVector(locationToIcRes(it))
        val markerOptions = MarkerOptions()
            .position(latLng)
            .title(it.name)
            .snippet(it.description)
            .icon(icon)

        addMarker(markerOptions).tag = it.type
    }

    val bounds = latLngBoundsbuilder.build()
    val newLatLngBounds = CameraUpdateFactory.newLatLngBounds(bounds, 160)
    animateCamera(newLatLngBounds)
    return newLatLngBounds
}

fun GoogleMap.setDefaults() {
    uiSettings.let {
        it.setAllGesturesEnabled(false)
        it.isMapToolbarEnabled = false
    }

}

private fun locationToIcRes(it: Location): Int {
    val vectorResId = when (it.type) {
        LocationType.CURRENT -> R.drawable.ic_current
        LocationType.PLATOON -> R.drawable.ic_truck
        LocationType.DESTINATION -> R.drawable.ic_flag
    }
    return vectorResId
}