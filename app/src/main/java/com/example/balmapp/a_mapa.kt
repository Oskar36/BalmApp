package com.example.balmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.balmapp.databinding.LMapaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class a_mapa : AppCompatActivity() , OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private var mapView: MapView? = null
    private var gmap: GoogleMap? = null
    private lateinit var binding: LMapaBinding
    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LMapaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }

        mapView =binding.mapa
        //Binding
        mapView!!.onCreate(mapViewBundle)
        mapView!!.getMapAsync(this)
    }
    //En caso de que haya problemas con el Bundle
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView!!.onSaveInstanceState(mapViewBundle)
    }
    //Funciones obligatorias de tener para que le mapa funcione
    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gmap = googleMap
        gmap!!.setMinZoomPreference(15f)
        val location1 = LatLng(   43.192611, -3.195056)
        val marker1 = MarkerOptions().position(location1).title("1.Gunea").snippet("Balmasedako Zubi Zaharra")
        gmap!!.addMarker(marker1)
        val location2 = LatLng(   43.201861, -3.249361)
        val marker2 = MarkerOptions().position(location2).title("2.Gunea").snippet("Kolitzako igoera")
        gmap!!.addMarker(marker2)
        val location2_1 = LatLng(   43.199778, -3.214444)
        val marker2_1 = MarkerOptions().position(location2_1).title("2.Gunea helmuga").snippet("Kolitza mendia")
        gmap!!.addMarker(marker2_1)
        val location3 = LatLng(   43.194064, -3.194186)
        val marker3 = MarkerOptions().position(location3).title("3.Gunea").snippet("Horcasitas Jauregia")
        gmap!!.addMarker(marker3)
        val location4 = LatLng(   43.192489, -3.197533)
        val marker4 = MarkerOptions().position(location4).title("4.Gunea").snippet("Aste Santuko Prozesioak")
        gmap!!.addMarker(marker4)
    }
}