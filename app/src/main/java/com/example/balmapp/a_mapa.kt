package com.example.balmapp


import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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
import com.google.android.material.navigation.NavigationView

class a_mapa : AppCompatActivity() , OnMapReadyCallback,NavigationView.OnNavigationItemSelectedListener  {
    private var mapView: MapView? = null
    private var gmap: GoogleMap? = null
    private lateinit var binding: LMapaBinding
    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"
    private lateinit var fusedLocation: FusedLocationProviderClient
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private var enrango=false
    private var toast1=false
    private var marcadores:MutableList<MarkerOptions> = mutableListOf()
    private var gunes_activos= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LMapaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if(marcadores.size!=0){
            marcadores.removeAll(marcadores)
        }
        fusedLocation= LocationServices.getFusedLocationProviderClient(this)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav)
        navigationView.bringToFront()
        val menu=navigationView.menu
//si el modo profesor esta activo se ven los elementos del menu o no
        if(Sharedapp.partida.partida=="profesor"){
            menu.findItem(R.id.puente_admin).isVisible=true
            menu.findItem(R.id.kolitxa_menu).isVisible=true
            menu.findItem(R.id.jauregi_menu).isVisible=true
            menu.findItem(R.id.procesion_menu).isVisible=true
            menu.findItem(R.id.txapela_menu).isVisible=true
            menu.findItem(R.id.san_felipe_menu).isVisible=true
            menu.findItem(R.id.putxero_menu).isVisible=true
            menu.findItem(R.id.modo_profesor_menuAdmin).isVisible=false
        }else{
            menu.findItem(R.id.puente_admin).isVisible=false
            menu.findItem(R.id.kolitxa_menu).isVisible=false
            menu.findItem(R.id.jauregi_menu).isVisible=false
            menu.findItem(R.id.procesion_menu).isVisible=false
            menu.findItem(R.id.txapela_menu).isVisible=false
            menu.findItem(R.id.san_felipe_menu).isVisible=false
            menu.findItem(R.id.putxero_menu).isVisible=false
        }
        mapView =binding.mapa
        //Binding
        mapView!!.onCreate(mapViewBundle)
        mapView!!.getMapAsync(this)
        // binding.toolbar.inflateMenu(R.menu.menu_admin)
        // abre el menu clickando el botón flotante
        binding.floatingActionButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        navigationView.setNavigationItemSelectedListener(this)

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
    //Funciones obligatorias de tener para que el mapa funcione
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

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        gmap = googleMap
        //insercciones de los marcadores
        insertarGune(LatLng(   43.192611, -3.195056),"1.Gunea","Balmasedako Zubi Zaharra",gmap!!)
        insertarGune(LatLng(   43.199778, -3.214444),"2.Gunea ","Kolitza mendia",gmap!!)
        insertarGune(LatLng(   43.194064, -3.194186),"3.Gunea","Horcasitas Jauregia",gmap!!)
        insertarGune(LatLng(   43.192489, -3.197533),"4.Gunea","Aste Santuko Prozesioak",gmap!!)
        insertarGune(LatLng(    43.188778, -3.200028),"5.Gunea","Boinas la Encartada Fabrika museoa",gmap!!)
        insertarGune(LatLng(    43.193611, -3.194861),"6.Gunea","San Felipe y Santiago eguna",gmap!!)
        insertarGune(LatLng(     43.196250, -3.192639),"7.Gunea","Balmasedako zaindariaren jaia: San Severino. Putxerak",gmap!!)
        //el zoom del mapa y la camara
        gmap!!.setMinZoomPreference(13f)
        gmap!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(     43.196250, -3.192639)))
        //comprueba si se han aceptado los permisos
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
                //en caso de que esté en modo guiado y ya se haya pasado los puntos abrirá directamente la pantalla de final de juego
            if(NavFrag.gune==7 && Sharedapp.partida.partida=="guiado"){
                NavFrag.IniciarActivity(this,"a_findejuego")
                finish()
            }
            gmap!!.setOnMarkerClickListener { marker ->
                if (marker.isInfoWindowShown) {
                    marker.hideInfoWindow()
                } else {
                    marker.showInfoWindow()
                }
                //si está en un rango de 50 metros del punto que toque, podrá abrir el juego clickando el marcador
                if (Sharedapp.partida.partida=="guiado"){
                    if(enrango && marker.title.toString().trim()==marcadores[NavFrag.gune].title.toString().trim()){
                        MarcadorJuego(marker.title.toString().trim())
                    }

                }
                //si está en un rango de 50 metros del punto que toque, podrá abrir el juego clickando el marcador
                else if(Sharedapp.partida.partida=="libre"){
                    if(gunes_activos.size!=0){
                        for(i in 0 until gunes_activos.size){
                            if (marker.title.equals(gunes_activos[i])){
                                MarcadorJuego(marker.title.toString().trim())
                                break
                            }
                        }
                        gunes_activos.removeAll(gunes_activos)
                    }
                }else{
                    //si está en modo profesor podrá abrir cualquiera
                    MarcadorJuego(marker.title.toString().trim())
                }
                true
            }
            if(Sharedapp.partida.partida=="guiado"||Sharedapp.partida.partida=="libre"){
                gmap!!.isMyLocationEnabled=true
                gmap!!.uiSettings.isZoomControlsEnabled=true
                gmap!!.uiSettings.isCompassEnabled=true
                //Localización a tiempo real
                gmap!!.setOnMyLocationChangeListener{
                    comprobarubicacion(it,Sharedapp.partida.partida)
                }
            }
        }
    }

    //comprueba la ubicacion y si esta a menos de 50 metros de el punto sale un mensaje de que ya se puede juegar
    private fun comprobarubicacion(location: Location, modo:String) {
        if(NavFrag.gune < marcadores.size-1){
            var localizacion=marcadores[NavFrag.gune].position
            var location_gune=Location("a")
            location_gune.latitude=localizacion.latitude
            location_gune.longitude=localizacion.longitude
            if(modo=="guiado"){
                if(location.distanceTo(location_gune)<=50){
                    enrango=true
                    if(!toast1){
                        Toast.makeText(this, R.string.iniciarjuego, Toast.LENGTH_SHORT).show()
                        toast1=true
                    }
                }else{
                    enrango=false
                    toast1=false
                }
            }else if(modo=="libre"){
                //se guardan todos los puntos que estén a 50 metros o menos
                for (i in 0 until marcadores.size){
                    localizacion=marcadores[i].position
                    location_gune=Location("a")
                    location_gune.latitude=localizacion.latitude
                    location_gune.longitude=localizacion.longitude
                    if(location.distanceTo(location_gune)<=50){
                        gunes_activos.add(marcadores[i].title!!)
                        marcadores[i].icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        enrango=true
                        if(!toast1){
                            Toast.makeText(this, R.string.iniciarjuego, Toast.LENGTH_SHORT).show()
                            toast1=true
                        }
                    }else{
                        // marcadores[i].icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        enrango=false
                    }
                }
            }
        }
    }

    // abre la pantalla correspondiente clickando en el marcador de cada juego en el mapa
    private fun MarcadorJuego(gune: String){
        when (gune){
            "1.Gunea" ->      abrirActivityMenu("a_juegos","puente")
            "2.Gunea" ->      abrirActivityMenu("a_juegos","kolitza")
            "3.Gunea" ->      abrirActivityMenu("a_juegos","jauregi")
            "4.Gunea" ->    abrirActivityMenu("a_juegos","procesion")
            "5.Gunea" ->      abrirActivityMenu("a_juegos","boina")
            "6.Gunea" ->   abrirActivityMenu("a_juegos","san felipe")
            "7.Gunea" ->      abrirActivityMenu("a_juegos","puchero")
        }
    }

    //dependiendo si esta en modo profesor o modo libre se pone de un color o otro los marcadores del mapa
    private fun insertarGune(location:LatLng, title:String, snippet:String, mapa:GoogleMap){
        val marcador = MarkerOptions().position(location).title(title).snippet(snippet)
        if(Sharedapp.partida.partida=="profesor"){
            marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        }else if(Sharedapp.partida.partida=="libre"){
            //poner colores al marcador en modo libre
            if(NavFrag.modo_libre.size!=0){
                if (NavFrag.modo_libre.contains(marcador.title.toString().trim())){
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                }else{
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                }
            }
        }else{
            //poner colores al marcador en modo guiado
            if(NavFrag.gune<marcadores.size){
                marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            }else if(NavFrag.gune==marcadores.size){
                marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            }else if(NavFrag.gune>marcadores.size){
                marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            }
        }
        mapa.addMarker(marcador)
        marcadores.add(marcador)
    }

    //al abrir menu las opciones que tiene te llevan a cada pantalla
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

    //abre la pantalla que se le pase
    private fun abrirActivityMenu(activity: String, juego:String){
        Sharedapp.prefs.juego=juego
        NavFrag.IniciarActivity(this,activity)
    }

    //dependiendo de que opcion del menu des te llevara al respectiva pantalla y abrira el dialogo de el modo profesor
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.puente_admin -> {abrirActivityMenu("a_juegos","puente")
                binding.drawerLayout.closeDrawer(GravityCompat.START)}
            R.id.kolitxa_menu -> {abrirActivityMenu("a_juegos","kolitza")
                binding.drawerLayout.closeDrawer(GravityCompat.START) }
            R.id.jauregi_menu ->  {abrirActivityMenu("a_juegos","jauregi")
                binding.drawerLayout.closeDrawer(GravityCompat.START) }
            R.id.procesion_menu -> {abrirActivityMenu("a_juegos","procesion")
                binding.drawerLayout.closeDrawer(GravityCompat.START) }
            R.id.txapela_menu -> {   abrirActivityMenu("a_juegos","boina")
                binding.drawerLayout.closeDrawer(GravityCompat.START) }
            R.id.san_felipe_menu -> {abrirActivityMenu("a_juegos","san felipe")
                binding.drawerLayout.closeDrawer(GravityCompat.START) }
            R.id.putxero_menu -> {abrirActivityMenu("a_juegos","puchero")
                binding.drawerLayout.closeDrawer(GravityCompat.START) }
            R.id.acerca_de_menu -> {abrirActivityMenu("a_acercade","puchero")
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.modo_profesor_menuAdmin -> { modo_Profesor()  }
            R.id.desconectar_menu -> {abrirActivityMenu("MainActivity","")
                binding.drawerLayout.closeDrawer(GravityCompat.START) }
        }
        return true
    }
    //si le das al boton para tras te lleva a el main activity
    override fun onBackPressed() {
        super.onBackPressed()
        NavFrag.IniciarActivity(this,"MainActivity")
        finish()
    }

    //este dialog tiene un plain text en el cual si es la contraseña correcta se pone el modo profesor
    private fun modo_Profesor(){
        val dialog = AlertDialog.Builder(this, R.style.DialogBasicCustomStyle)
        val view = LayoutInflater.from(this).inflate(R.layout.l_dialogo_profesor, null)
        dialog
            .setView(view)
            .setPositiveButton(R.string.continuar,
                DialogInterface.OnClickListener { dialog, id ->
                    Sharedapp.partida.partida="profesor"
                    finish()
                    NavFrag.IniciarActivity(this,"a_mapa")
                    dialog.dismiss()
                })
            .setNeutralButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }
}