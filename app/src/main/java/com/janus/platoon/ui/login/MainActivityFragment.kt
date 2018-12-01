package com.janus.platoon.ui.login

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
    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val locations = ArrayList<Location>()
        locations.add(Location(10.0, 10.0, "name1", "desc1", LocationType.CURRENT))
        locations.add(Location(11.0, 11.0, "name2", "desc2", LocationType.PLATOON))
        locations.add(Location(12.0, 12.0, "name3", "desc3", LocationType.PLATOON))
        locations.add(Location(13.0, 13.0, "name4", "desc4", LocationType.DESTINATION))
        mMap.addMarkersAndMoveCamera(context!!, locations)

    }
}


