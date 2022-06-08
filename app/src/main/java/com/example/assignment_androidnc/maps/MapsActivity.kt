package com.example.assignment_androidnc.maps

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.example.assignment_androidnc.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.assignment_androidnc.databinding.ActivityMapsBinding
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val searchView = binding.mapSearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val location = searchView.query.toString()
                var addressList = ArrayList<Address>()
                if(location!=""){
                    val geocoder = Geocoder(this@MapsActivity)
                    try {
                        addressList = (geocoder.getFromLocationName(location,1) as ArrayList<Address>?)!!
                    } catch (e:IOException){
                        e.printStackTrace()
                    }
                    var address = addressList[0]
                    val latLng = LatLng(address.latitude,address.longitude)
                    mMap.addMarker(MarkerOptions().position(latLng).title(location))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,14f))
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val vietnam = LatLng(21.0227788,105.8194541)
        mMap.addMarker(MarkerOptions().position(vietnam).title("Marker in Vietnam"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vietnam,14f))
    }
}