package com.janus.platoon.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.janus.platoon.R
import com.janus.platoon.base.BaseFragment
import com.janus.platoon.data.Location
import com.janus.platoon.data.LocationType
import com.janus.platoon.databinding.FragmentMainBinding
import com.janus.platoon.util.addMarkersAndMoveCamera
import com.janus.platoon.vm.MainVM


class MainActivityFragment : BaseFragment<MainVM, FragmentMainBinding>(), OnMapReadyCallback {
    override val getLayoutId: Int = R.layout.fragment_main
    override val viewModelClass = MainVM::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val locations = ArrayList<Location>()
        locations.add(Location(41.0049823,28.731987, "name1", "desc1", LocationType.CURRENT))
        locations.add(Location(45.8401104,15.8242464, "name2", "desc2", LocationType.PLATOON))
        locations.add(Location(48.1548256,11.4017511, "name3", "desc3", LocationType.PLATOON))
        locations.add(Location(48.6431786,9.3470013, "name4", "desc4", LocationType.DESTINATION))
        googleMap.addMarkersAndMoveCamera(context!!, locations)

    }
}


