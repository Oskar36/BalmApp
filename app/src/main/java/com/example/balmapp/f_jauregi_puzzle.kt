package com.example.balmapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner





import com.example.balmapp.databinding.LJauregiPuzzleBinding


private var _binding: LJauregiPuzzleBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
private var aciertos:Int=0

class f_jauregi_puzzle : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LJauregiPuzzleBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        aciertos=0
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imgjauregiJuegoLogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_jauregia)
        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imgjauregiJuegoLogo)
        }

        //convertir las piezas en arrastables
        binding.imgPiezaBalcon.setOnTouchListener(touchListener)
        binding.imgPiezaColumna.setOnTouchListener(touchListener)
        binding.imgPiezaEscudo.setOnTouchListener(touchListener)
        binding.imgPiezaPuertaprincipal.setOnTouchListener(touchListener)
        binding.imgPiezaVentana.setOnTouchListener(touchListener)
        binding.imgPiezaPuertaventana.setOnTouchListener(touchListener)


        //parar y continuar el audio
        binding.imgjauregiJuegoLogo.setOnClickListener {
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imgjauregiJuegoLogo)
                mediaplayer!!.pause()
            }else{
                if(mediaplayer!!.currentPosition!=0 && mediaplayer!!.currentPosition!= mediaplayer!!.duration){
                    mediaplayer!!.seekTo(mediaplayer!!.currentPosition)
                }
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.imgjauregiJuegoLogo)
            }
        }

    }

    private var xDelta: Int = 0
    private var yDelta: Int = 0
    private var x0 = 0.0f
    private var y0 = 0.0f

    @SuppressLint("ClickableViewAccessibility")
    val touchListener = View.OnTouchListener { view, event ->
        val x = event.rawX.toInt()
        val y = event.rawY.toInt()
        if (x0 == 0.0f) {
            x0 = view.x
            y0 = view.y
        }
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val lParams = view.layoutParams as ConstraintLayout.LayoutParams
                xDelta = x - lParams.leftMargin
                yDelta = y - lParams.topMargin
            }
            //evento cuando dejamos el objeto en un punto de la pantalla
            MotionEvent.ACTION_UP -> {

                //guardamos las posiciones x, y  de el objeto
                val location_pieza = IntArray(2)
                view.getLocationOnScreen(location_pieza)
                val x_pieza = location_pieza[0]
                val y_pieza = location_pieza[1]


                //guardamos las posiciones x, y de donde tiene que ir cada pieza en funcion de la pieza que este este seleccionada
                val x_imagen =   posicion_pieza(resources.getResourceEntryName(view.id))[0]
                val y_imagen =   posicion_pieza(resources.getResourceEntryName(view.id))[1]
                //Ajustar el campo donde se puede quedar la pieza
                if ((x_pieza <= (x_imagen + 100) && x_pieza >= (x_imagen - 100)) && (y_pieza <= (y_imagen + 100) && (y_pieza >= y_imagen - 100))) {
                    //visibilidad de que la pieza esta en el sitio correcto
                    //poner filtro verde en la posicion de la pieza
                    poner_filtro_normal(resources.getResourceEntryName(view.id))
                    Valid()

                    //desaparece la pieza
                    view.isVisible = false
                }
                else{
                    view.x=x0
                    view.y=y0
                    y0=0.0f
                    x0=0.0f
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val layoutParams = view
                    .layoutParams as ConstraintLayout.LayoutParams
                layoutParams.leftMargin = x - xDelta
                layoutParams.topMargin = y - yDelta
                layoutParams.rightMargin = 0
                layoutParams.bottomMargin = 0
                view.layoutParams = layoutParams
            }
        }
        binding.frameLayout7.invalidate()
        true
    }


    //dependiendo de la pieza seleccionada marcamos la posicion de la pieza

    private fun posicion_pieza(nombre:String):IntArray {

        val location_pieza = IntArray(2)
        when (nombre) {

            "img_pieza_puertaprincipal" ->{
                binding.posicionPuertaprincipal.getLocationOnScreen(location_pieza)
            return location_pieza}
            "img_pieza_balcon" ->{
            binding.posicionBalcon.getLocationOnScreen(location_pieza)
            return location_pieza}
            "img_pieza_ventana" ->{
            binding.posicionVentana.getLocationOnScreen(location_pieza)
            return location_pieza}
            "img_pieza_puertaventana" ->{
            binding.posicionPuertaventana.getLocationOnScreen(location_pieza)
            return location_pieza}
            "img_pieza_escudo" ->{
            binding.posicionEscudo.getLocationOnScreen(location_pieza)
            return location_pieza}
            "img_pieza_columna" ->{
                binding.posicionColumna.getLocationOnScreen(location_pieza)

                return location_pieza}

        }
        return location_pieza
    }



    private fun poner_filtro_normal(nombre:String) {

        when (nombre) {

            "img_pieza_puertaprincipal" ->{
                binding.posicionPuertaprincipal.setImageDrawable(getResources().getDrawable(R.drawable.pieza_puertaprincipal))
                }
            "img_pieza_balcon" ->{
                binding.posicionBalcon.setImageDrawable(getResources().getDrawable(R.drawable.pieza_balcon))

                }
            "img_pieza_ventana" ->{
                binding.posicionVentana.setImageDrawable(getResources().getDrawable(R.drawable.pieza_ventana))

                }
            "img_pieza_puertaventana" ->{
                binding.posicionPuertaventana.setImageDrawable(getResources().getDrawable(R.drawable.pieza_puertaventana))

               }
            "img_pieza_escudo" ->{
                binding.posicionEscudo.setImageDrawable(getResources().getDrawable(R.drawable.pieza_escudo))

               }
            "img_pieza_columna" ->{
                binding.posicionColumna.setImageDrawable(getResources().getDrawable(R.drawable.pieza_columna))

           }

        }

    }



    override fun onStart() {
        super.onStart()
        //Iniciamos el audio
        mediaplayer!!.start()

    }


    override fun onStop() {
        super.onStop()
        //liberacion del productor de medios
        mediaplayer?.release()
        mediaplayer = null


    }
    private fun mostrarDialogoPersonalizado(){

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_siguientejuego,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.AbrirSiguiente(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Juego1")
                    // sign in the user ...
                })
            .setNeutralButton(R.string.repetir,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.MarcadorJuegofinintermedio(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }
//controlamos que todas las piezas esten colocadas
    private fun Valid():Boolean{

    aciertos += 1
        if (aciertos==6){
            mediaplayer!!.stop()
            Sharedapp.gune.gune="3.Gunea 1"
            SystemClock.sleep(1000)
            descargar()
        }
        return true
    }


    private fun descargar(){
        val bm = BitmapFactory.decodeResource(resources, R.drawable.palaciohorcasitasbalmaseda)

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogo_descargar,null))
            .setPositiveButton(R.string.si,
                DialogInterface.OnClickListener { dialog, id ->


                    MediaStore.Images.Media.insertImage(requireActivity().contentResolver,bm, "Puzzle" , "Puzzle jauregi")
                    dialog.dismiss()
                    Toast.makeText(requireContext(), resources.getString(R.string.desgargado), Toast.LENGTH_SHORT).show()
                    SystemClock.sleep(1000)
                    mostrarDialogoPersonalizado()
                    // sign in the user ...
                })
            .setNeutralButton(R.string.no,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                    SystemClock.sleep(1000)
                    mostrarDialogoPersonalizado()
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }
}