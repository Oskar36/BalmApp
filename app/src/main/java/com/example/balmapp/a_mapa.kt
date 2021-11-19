package com.example.balmapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import com.example.balmapp.databinding.LMapaBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class a_mapa : AppCompatActivity() , OnMapReadyCallback {
    private var mapView: MapView? = null
    private var gmap: GoogleMap? = null
    private lateinit var binding: LMapaBinding
    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"
    private lateinit var fusedLocation: FusedLocationProviderClient
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_admin, menu)
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LMapaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        fusedLocation= LocationServices.getFusedLocationProviderClient(this)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }

        mapView =binding.mapa
        //Binding
        mapView!!.onCreate(mapViewBundle)
        mapView!!.getMapAsync(this)
       // binding.toolbar.inflateMenu(R.menu.menu_admin)


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
        insertarGune(LatLng(   43.192611, -3.195056),"1.Gunea","Balmasedako Zubi Zaharra",gmap!!)
        insertarGune(LatLng(   43.201861, -3.249361),"2.Gunea","Kolitzako igoera",gmap!!)
        insertarGune(LatLng(   43.199778, -3.214444),"2.Gunea helmuga","Kolitza mendia",gmap!!)
        insertarGune(LatLng(   43.194064, -3.194186),"3.Gunea","Horcasitas Jauregia",gmap!!)
        insertarGune(LatLng(   43.192489, -3.197533),"4.Gunea","Aste Santuko Prozesioak",gmap!!)
        insertarGune(LatLng(   43.176194, -3.212556),"5.Gunea","Boinas la Encartada Fabrika museoa",gmap!!)
        insertarGune(LatLng(    43.188778, -3.200028),"5.Gunea helmuga","Boinas la Encartada Fabrika museoa",gmap!!)
        insertarGune(LatLng(    43.193611, -3.194861),"6.Gunea","San Felipe y Santiago eguna",gmap!!)
        insertarGune(LatLng(     43.196250, -3.192639),"7.Gunea","Balmasedako zaindariaren jaia: San Severino. Putxerak",gmap!!)
        gmap!!.setMinZoomPreference(12f)
        gmap!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(     43.196250, -3.192639)))
        gmap!!.setOnMarkerClickListener { marker ->
            if (marker.isInfoWindowShown) {
                marker.hideInfoWindow()
            } else {
                marker.showInfoWindow()
            }
            MarcadorJuego(marker.title.toString())
            true
         }
        gmap!!.isMyLocationEnabled=true
        gmap!!.uiSettings.isZoomControlsEnabled=true
        gmap!!.uiSettings.isCompassEnabled=true
        //Cuando se aceptan los permisos
        fusedLocation.lastLocation.addOnSuccessListener {
            if (it != null) {
                val ubicacion = LatLng(it.latitude, it.longitude)
                val marcador = MarkerOptions().position(ubicacion).title("Mi ubicación")
                marcador.icon(BitmapDescriptorFactory.fromResource(R.drawable.logomapa))
                gmap!!.addMarker(marcador)
              gmap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 12f))
            }

        }
        //Localización a tiempo real
        gmap!!.setOnMyLocationChangeListener{
            val ubicacion = LatLng(it.latitude, it.longitude)
            gmap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,20f))
        }
    }
    private fun insertarGune(location:LatLng, title:String, snippet:String, mapa:GoogleMap){
        val marcador = MarkerOptions().position(location).title(title).snippet(snippet)
        marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        mapa.addMarker(marcador)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.puente_admin ->      abrirActivityMenu("a_juegos","puente")
            R.id.kolitxa_menu ->      abrirActivityMenu("a_juegos","kolitza")
            R.id.jauregi_menu ->      abrirActivityMenu("a_juegos","jauregi")
            R.id.procesion_menu ->    abrirActivityMenu("a_juegos","procesion")
            R.id.san_felipe_menu ->   abrirActivityMenu("a_juegos","san felipe")
            R.id.putxero_menu ->      abrirActivityMenu("a_juegos","puchero")
            R.id.txapela_menu ->      abrirActivityMenu("a_juegos","boina")
            R.id.acerca_de_menu ->    abrirActivityMenu("a_acercade","")
            R.id.desconectar_menu ->  abrirActivityMenu("MainActivity","")
        }
        return true
    }
    private fun abrirActivityMenu(activity: String, juego:String){
        Sharedapp.prefs.juego=juego
        NavFrag.IniciarActivity(this,activity)
    }
    private fun MarcadorJuego(gune: String){
        when (gune){
            "1.Gunea" ->      abrirActivityMenu("a_juegos","puente")
            "2.Gunea helmuga" ->      abrirActivityMenu("a_juegos","kolitza")
            "3.Gunea" ->      abrirActivityMenu("a_juegos","jauregi")
            "4.Gunea" ->    abrirActivityMenu("a_juegos","procesion")
            "5.Gunea helmuga" ->      abrirActivityMenu("a_juegos","boina")
            "6.Gunea" ->   abrirActivityMenu("a_juegos","san felipe")
            "7.Gunea" ->      abrirActivityMenu("a_juegos","puchero")
        }
    }
}

