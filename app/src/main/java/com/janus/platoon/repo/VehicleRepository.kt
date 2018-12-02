package com.janus.platoon.repo

import android.text.TextUtils
import com.janus.platoon.data.Vehicle
import com.janus.platoon.remote.DataHolder
import com.janus.platoon.util.Status
import javax.inject.Inject

class VehicleRepository @Inject constructor() {
    private lateinit var vehicles: List<Vehicle>
    private lateinit var currentVehicle: Vehicle

    fun save(dataHolder: DataHolder<List<Vehicle>>): Status {
        when (dataHolder) {
            is DataHolder.Success -> {
                dataHolder.data.let {
                    vehicles = it
                    if (!it.isNullOrEmpty()) {
                        it.forEach {
                            if (it.isMe()) {
                                currentVehicle = it
                            }
                        }
                    }
                    return Status.FINISHED
                }
            }
            is DataHolder.Error -> {
                return Status.FAILED.withReason(dataHolder.error.message)
            }
        }
    }

    fun getCurrentVehicle(): Vehicle {
        return currentVehicle
    }

    fun getVehicles(): List<Vehicle> {
        return vehicles
    }
}

fun Vehicle.isMe(): Boolean {
    return TextUtils.equals(vehicleId, "34ZRR34")
}