package com.janus.platoon.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.janus.platoon.R
import com.janus.platoon.data.Location
import com.janus.platoon.data.LocationType

fun GoogleMap.addMarkersAndMoveCamera(context: Context, locations: List<Location>) {
    val latLngBoundsbuilder = LatLngBounds.Builder()
    locations.forEach {
        val latLng = LatLng(it.lat, it.lon)
        latLngBoundsbuilder.include(latLng)
        val icon = bitmapDescriptorFromVector(context,locationToIcRes(it))
        val markerOptions = MarkerOptions()
            .position(latLng)
            .title(it.name)
            .snippet(it.description)
            .icon(icon)

        addMarker(markerOptions)
    }

    val bounds = latLngBoundsbuilder.build()
    animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 160));
}

private fun locationToIcRes(it: Location): Int {
    val vectorResId = when (it.type) {
        LocationType.CURRENT -> R.drawable.ic_current
        LocationType.PLATOON -> R.drawable.ic_truck
        LocationType.DESTINATION -> R.drawable.ic_flag
    }
    return vectorResId
}

fun bitmapDescriptorFromVector(context: Context,vectorResId: Int): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}