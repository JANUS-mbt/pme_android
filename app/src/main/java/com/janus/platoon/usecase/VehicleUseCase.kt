package com.janus.platoon.usecase

import com.janus.platoon.data.Location
import com.janus.platoon.data.LocationType
import com.janus.platoon.remote.NetworkHandler
import com.janus.platoon.repo.VehicleRepository
import com.janus.platoon.repo.isMe
import com.janus.platoon.util.Status
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class VehicleUseCase
@Inject constructor(
    val networkHandler: NetworkHandler,
    val vehicleRepository: VehicleRepository
) {
    val status = PublishSubject.create<Status>()

    fun getVehicles(): Flowable<Status> {
        return networkHandler
            .getVehicles()
            .map(vehicleRepository::save)
            .doOnNext(::updateStatus)
            .doOnError { it -> updateStatus(Status.FAILED.withReason(it.message)) }
    }

    fun getCurrentVehicleInfo(): List<Location> {
        val locations = ArrayList<Location>()
        vehicleRepository.getCurrentVehicle().let {
            locations.add(
                Location(
                    it.vehicleLocationLatitude.toDouble(),
                    it.vehicleLocationLongitude.toDouble(),
                    it.vehicleName,
                    "Current Location",
                    LocationType.CURRENT
                )
            )
            locations.add(
                Location(
                    it.vehicleDestinationLatitude.toDouble(),
                    it.vehicleDestinationLongitude.toDouble(),
                    it.vehicleName,
                    "Destination",
                    LocationType.DESTINATION
                )
            )
        }
        return locations
    }

    fun getLocations(): ArrayList<Location> {
        val locations = ArrayList<Location>()
        vehicleRepository.getVehicles().forEach {
            locations.add(
                Location(
                    it.vehicleLocationLatitude.toDouble(),
                    it.vehicleLocationLongitude.toDouble(),
                    it.vehicleName,
                    if (it.isMe()) "Current Location" else it.vehicleDescription,
                    if (it.isMe()) LocationType.CURRENT else LocationType.PLATOON
                )
            )
        }
        return locations
    }

    private fun updateStatus(status: Status) {
        this.status.onNext(status)
    }
}