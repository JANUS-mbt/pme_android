package com.janus.platoon.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.janus.platoon.R
import com.janus.platoon.base.BaseFragment
import com.janus.platoon.databinding.FragmentMainBinding
import com.janus.platoon.util.Status
import com.janus.platoon.util.addMarkersAndMoveCamera
import com.janus.platoon.util.setDefaults
import com.janus.platoon.vm.MainVM


class MainActivityFragment : BaseFragment<MainVM, FragmentMainBinding>(), OnMapReadyCallback {
    override val getLayoutId: Int = R.layout.fragment_main
    override val viewModelClass = MainVM::class.java
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.status.observe(this, Observer {
            when (it) {
                Status.FINISHED -> updateUi()
                Status.FAILED -> Toast.makeText(context, it.reason as String, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        googleMap.setDefaults()
        googleMap.setOnMapLoadedCallback { updateUi() }
    }

    private fun updateUi() {
        if (viewModel.status.value == Status.FINISHED && mMap != null) {
            val cameraUpdate = mMap?.addMarkersAndMoveCamera(context!!, viewModel.getLocations())
            mMap?.setOnMapClickListener { mMap?.animateCamera(cameraUpdate) }
        }
    }

}


