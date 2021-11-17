package com.example.balmapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible

import com.example.balmapp.databinding.LJauregiPuzzleBinding


private var _binding: LJauregiPuzzleBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_jauregi_puzzle : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LJauregiPuzzleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnHurrengoa.setOnClickListener() {
            val fragment: Fragment = f_jauregi_unirjuego()
            NavFrag.replaceFragment(
                fragment,
                requireActivity(),
                ((view as ViewGroup).parent as View).id
            )
            //paramos el audio
            mediaplayer!!.stop()
        }
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
                mediaplayer!!.stop()
            }else{

                mediaplayer!!.prepare()
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.imgjauregiJuegoLogo)
            }
        }

    }

    var xDelta: Int = 0
    var yDelta: Int = 0
    var x0 = 0.0f
    var y0 = 0.0f

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
                if ((x_pieza <= (x_imagen + 50) && x_pieza >= (x_imagen - 50)) && (y_pieza <= (y_imagen + 50) && (y_pieza >= y_imagen - 50))) {

                    //visibilidad de que la pieza esta en el sitio correcto
                    //poner filtro verde en la posicion de la pieza
                    poner_filtro_verde(resources.getResourceEntryName(view.id))

                    //desaparece la pieza
                    view.isVisible = false



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

    fun posicion_pieza(nombre:String):IntArray {

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
            "img_pieza_balcon" ->{
            binding.posicionBalcon.getLocationOnScreen(location_pieza)
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
    fun poner_filtro_verde(nombre:String) {

        when (nombre) {

            "img_pieza_puertaprincipal" ->{
                binding.posicionPuertaprincipal.setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN)
                }
            "img_pieza_balcon" ->{
                binding.posicionBalcon.setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN)
                }
            "img_pieza_ventana" ->{
                binding.posicionVentana.setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN)
                }
            "img_pieza_balcon" ->{
                binding.posicionBalcon.setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN)
                }
            "img_pieza_puertaventana" ->{
                binding.posicionPuertaventana.setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN)
               }
            "img_pieza_escudo" ->{
                binding.posicionEscudo.setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN)
               }
            "img_pieza_columna" ->{
            binding.posicionColumna.setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN)
           }

        }

    }

        





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
}