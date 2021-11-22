package com.example.balmapp

import android.animation.Animator
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
import android.view.animation.Animation

import android.view.animation.Animation.AnimationListener





private var _binding: LPutxeroJuegoBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
private var mediaplayerFallo:MediaPlayer? = null
private var mediaplayerTren:MediaPlayer?=null
private var aciertos:Int=0
class f_putxero_juego : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LPutxeroJuegoBinding.inflate(inflater, container, false)
        return  binding.root
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        aciertos=0
        binding.btnPutxerojuego.setOnClickListener{ //paramos el audio
            mediaplayer!!.stop()
            mediaplayerFallo!!.stop()
            mediaplayerTren!!.stop()
            Sharedapp.gune.gune="7.Gunea"
            val fragment:Fragment=f_fin()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imgputxeroJuegoLogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_putxeroa)
        mediaplayerFallo = MediaPlayer.create(context,R.raw.megamanxerror)
        mediaplayerTren = MediaPlayer.create(context,R.raw.sonidotren)
        val moveLefttoRight = TranslateAnimation(600F, -420F, 0F, 0F)
        moveLefttoRight.duration = 50000
        moveLefttoRight.fillAfter = true
        binding.imgputxeroJuegoLogo.setOnClickListener{
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imgputxeroJuegoLogo)
               mediaplayer!!.stop()
            }else{
                mediaplayer!!.prepare()
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.imgputxeroJuegoLogo)
            }
        }
        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imgputxeroJuegoLogo)
            binding.imgPutxeroTren.startAnimation(moveLefttoRight)

            //Esto configura el movimiento de todos los alimentos
            binding.imgAlubias.setOnTouchListener(touchListener)
            binding.imgPimiento.setOnTouchListener(touchListener)
            binding.imgMorcilla.setOnTouchListener(touchListener)
            binding.imgAceite.setOnTouchListener(touchListener)
            binding.imgArroz.setOnTouchListener(touchListener)
            binding.imgCebolla.setOnTouchListener(touchListener)
            binding.imgChorizo.setOnTouchListener(touchListener)
            binding.imgCostilla.setOnTouchListener(touchListener)
            binding.imgLechuga.setOnTouchListener(touchListener)
            binding.imgPepinillo.setOnTouchListener(touchListener)
            binding.imgSal.setOnTouchListener(touchListener)
            binding.imgZanahoria.setOnTouchListener(touchListener)
            binding.imgTomate.setOnTouchListener(touchListener)
            mediaplayerTren!!.start()
            moveLefttoRight.setAnimationListener(object : AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                }

                override fun onAnimationRepeat(animation: Animation) {
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Pass the Intent to switch to other Activity
                    mediaplayer!!.stop()
                    mediaplayerFallo!!.stop()
                    mediaplayerTren!!.stop()
                    Sharedapp.gune.gune="7.Gunea"
                    val fragment:Fragment=f_fin()
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)                }
            })
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private var xDelta: Int=0
    private var yDelta: Int=0
    private var x0=0.0f
    private var y0=0.0f
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
                        y0=0.0f
                        x0=0.0f
                    }else{
                        view.x=x0
                        view.y=y0
                        y0=0.0f
                        x0=0.0f
                        mediaplayerFallo!!.start()
                    }
                }else{
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
        mediaplayerFallo?.release()
        mediaplayerTren?.release()
        mediaplayerFallo=null
        mediaplayer = null
        mediaplayerTren=null
    }


    //Esto configura las tres imegenes que no deben entrar al puchero
    private fun Valid(nombre:String):Boolean{
        val array_no_validos:List<String> = listOf("lechuga","pepinillo", "arroz")
        array_no_validos.forEach{
            var comprnombre="img_"
            comprnombre += it
            if(comprnombre == nombre){
                return false
            }
        }
         if (aciertos==9){
            mediaplayer!!.stop()
            mediaplayerFallo!!.stop()
            mediaplayerTren!!.stop()
            Sharedapp.gune.gune="7.Gunea"
            val fragment:Fragment=f_fin()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }else{
            aciertos++
        }
        return true
    }
}