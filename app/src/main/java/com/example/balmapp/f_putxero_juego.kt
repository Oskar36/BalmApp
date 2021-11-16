package com.example.balmapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LPutxeroJuegoBinding
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.*


private var _binding: LPutxeroJuegoBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
class f_putxero_juego : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LPutxeroJuegoBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnPutxerojuego.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
            //paramos el audio
            mediaplayer!!.stop()
        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imgputxeroJuegoLogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.kolitza_azalpena)


        val moveLefttoRight = TranslateAnimation(600F, -420F, 0F, 0F)
        moveLefttoRight.duration = 50000
        moveLefttoRight.fillAfter = true

        binding.imgputxeroJuegoLogo.setOnClickListener(){
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imgputxeroJuegoLogo)
               mediaplayer!!.stop()
            }else{
                NavFrag.animacion_dantzaris(binding.imgputxeroJuegoLogo)
                mediaplayer!!.start()
            }
        }
        binding.imgPutxeroTren.startAnimation(moveLefttoRight)
        binding.imgAlubias.setOnTouchListener(touchListener)
        binding.imgEspinacas.setOnTouchListener(touchListener)
        binding.imgManzana.setOnTouchListener(touchListener)
        binding.imgHuevos.setOnTouchListener(touchListener)
        binding.imgMorcilla.setOnTouchListener(touchListener)
        binding.imgPatatas.setOnTouchListener(touchListener)
        binding.imgCaramelos.setOnTouchListener(touchListener)
        binding.imgPimiento.setOnTouchListener(touchListener)

        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imgputxeroJuegoLogo)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    var xDelta: Int=0
    var yDelta: Int=0
    var x0=0.0f
    var y0=0.0f
    @SuppressLint("ClickableViewAccessibility")
    val touchListener = View.OnTouchListener { view, event ->
        val x = event.rawX.toInt()
        val y = event.rawY.toInt()
        if(x0==0.0f){
            x0=view.x
            y0=view.y
        }
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val lParams = view.layoutParams as ConstraintLayout.LayoutParams
                xDelta = x - lParams.leftMargin
                yDelta = y - lParams.topMargin
            }
            MotionEvent.ACTION_UP -> {
                val location = IntArray(2)
                view.getLocationOnScreen(location)
                val x = location[0]
                val y = location[1]
                val location2 = IntArray(2)
                binding.imgPutxeroPutxero.getLocationOnScreen(location2)
                val x2 = location2[0]
                val y2 = location2[1]

                if((x<=(x2+100) && x>=(x2-100)) && (y<=(y2+300) && (y>=y2-40))){
                    if(Valid(resources.getResourceEntryName(view.id))){
                        view.isVisible=false
                    }else{
                        view.x=x0
                        view.y=y0
                    }
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
       binding.frameLayout11.invalidate()
        true
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

    fun Valid(nombre:String):Boolean{
        val array_no_validos:List<String> = listOf("manzana","caramelos")
        array_no_validos.forEach{
            var comprnombre:String="img_"
            comprnombre += it
            if(comprnombre == nombre){
                return false
            }
        }
        return true
    }
}