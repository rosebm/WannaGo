package com.rosalynbm.wannago2.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.rosalynbm.wannago2.BuildConfig
import com.rosalynbm.wannago2.R
import com.rosalynbm.wannago2.base.BaseFragment
import com.rosalynbm.wannago2.databinding.FragmentMapsBinding
import com.rosalynbm.wannago2.model.PoiItem
import org.koin.android.ext.android.inject
import java.util.*

class MapsFragment : BaseFragment(), View.OnClickListener {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: FragmentMapsBinding
    private var map: GoogleMap? = null

    override val _viewModel: MapViewModel by inject()

    companion object {
        private const val PERMISSION_ID = 19
        private const val ZOOM_LEVEL = 15f
    }

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, the marker is based on your last known location.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        map = googleMap
        enableMyLocation()
        setOnMapPoiClickListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        binding.mapSaveButton.setOnClickListener(this)

        initSupportMapFragment()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        return binding.root
    }

    private fun initSupportMapFragment() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    // method to request for permissions
    private fun requestPermissions() {
        requestPermissions(arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_ID)
    }

    private fun isLocationEnabled(): Boolean {
        var enabled = false
        val locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        locationManager?.let {
            enabled = it.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    it.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }

        return enabled
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.mapSaveButton -> onLocationSelected()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation()
            } else {
                // Display a snackbar explaining that the user needs location permissions in order to
                // trigger the reminders
                Snackbar.make(
                    binding.root,
                    R.string.permission_denied_explanation,
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction(R.string.settings) {
                        startActivity(Intent().apply {
                            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    }.show()
            }
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        var latLng: LatLng

        if (isPermissionGranted()) {
            map?.isMyLocationEnabled = true

            // Subscribe to location changes
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    // Got last known location. In some rare situations this can be null.
                    location?.let {
                        latLng = LatLng(location.latitude, location.longitude)

                        map?.let {
                            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL))
                            it.addMarker(MarkerOptions().position(latLng).title("Marker in user's last location"))
                        }
                        Toast.makeText(requireContext(), "Please select a location", Toast.LENGTH_LONG).show()
                    }
                }
        }
        else {
            requestPermissions()
        }
    }

    private fun setOnMapPoiClickListener() {
        map?.setOnPoiClickListener { poi ->
            //displays additional info
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                poi.latLng.latitude,
                poi.latLng.longitude
            )
            map?.addMarker(
                MarkerOptions()
                    .position(poi.latLng)
                    .title(poi.name)
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )

            _viewModel.selectedPOI.value = poi
            _viewModel.placeLocationName.value = poi.name
            _viewModel.latitude.value = poi.latLng.latitude
            _viewModel.longitude.value = poi.latLng.longitude
            _viewModel.placeId.value = poi.placeId
        }
    }

    private fun onLocationSelected() {
        createPlace().let { place ->
            _viewModel.validateAndSaveReminder(place)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //make sure to clear the view model after destroy, as it's a single view model.
        _viewModel.onClear()
    }

    private fun createPlace(): PoiItem {
        val location = _viewModel.placeLocationName.value
        val description = _viewModel.placeDescription.value
        val latitude = _viewModel.latitude.value
        val longitude = _viewModel.longitude.value
        val placeId = _viewModel.placeId.value

        return PoiItem(
            location,
            description,
            latitude,
            longitude,
            placeId
        )
    }

}