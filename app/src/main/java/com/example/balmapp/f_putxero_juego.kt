package com.example.balmapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LPutxeroJuegoBinding
import android.view.animation.TranslateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.*
import android.view.animation.Animation

import android.view.animation.Animation.AnimationListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import android.media.AudioManager
import android.media.ToneGenerator.MAX_VOLUME
import android.os.Handler


private var _binding: LPutxeroJuegoBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
private var mediaplayerFallo:MediaPlayer? = null
private var mediaplayerTren:MediaPlayer?=null
private var aciertos:Int=0
private var victoria:Boolean = false
private var saltar=false
private var terminar=false
private lateinit var   moveLefttoRight:TranslateAnimation
class f_putxero_juego : Fragment() {
    override fun onCreateView(
        //asignacion del layout
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
        victoria=false
        saltar=false
        terminar=false
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imgputxeroJuegoLogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_putxeroa)
        mediaplayerFallo = MediaPlayer.create(context,R.raw.megamanxerror)
        mediaplayerTren = MediaPlayer.create(context,R.raw.sonidotren)
        //Iniciamos el audio
        mediaplayer!!.start()
        moveLefttoRight = TranslateAnimation(600F, -120F, 0F, 0F)
        moveLefttoRight.duration = 50000
        moveLefttoRight.fillAfter = true

        //Toast.makeText(requireContext(), moveLefttoRight.duration.toString() , Toast.LENGTH_SHORT).show()
        binding.imgputxeroJuegoLogo.setOnClickListener{
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imgputxeroJuegoLogo)
                mediaplayer!!.pause()
            }else{
                if(mediaplayer!!.currentPosition!=0 && mediaplayer!!.currentPosition!= mediaplayer!!.duration){
                    mediaplayer!!.seekTo(mediaplayer!!.currentPosition)
                }
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.imgputxeroJuegoLogo)
            }
        }

        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {

            val handler = Handler()
            handler.postDelayed(Runnable {
                NavFrag.animacion_numero(binding.numeros)

            }, 47000)

            if(!saltar){
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
                mediaplayerTren!!.setVolume(0.7F, 0.7F)
                saltar=true
                terminar=true

            }

            moveLefttoRight.setAnimationListener(object : AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                }

                override fun onAnimationRepeat(animation: Animation) {
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Pass the Intent to switch to other Activity
                    NavFrag.animacion_dantzaris_parar(binding.imgputxeroJuegoLogo)
                    mediaplayer!!.stop()
                    mediaplayerFallo!!.stop()
                    mediaplayerTren!!.stop()
                    Sharedapp.gune.gune="7.Gunea"
                    if(!victoria){
                        SystemClock.sleep(1000)
                        mostrarDialogoDerrota()
                    }
                }
            })

        }

        binding.saltar.setOnClickListener {
            if (!terminar){
                saltar=false
                mediaplayer!!.seekTo(mediaplayer!!.duration)
                NavFrag.animacion_dantzaris_parar(binding.imgputxeroJuegoLogo)
            }
        }


    }

    private var xDelta: Int=0
    private var yDelta: Int=0
    private var x0=0.0f
    private var y0=0.0f
    @SuppressLint("ClickableViewAccessibility")
    //si
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
                val x_ingrediente=view.x
                val y_ingrediente=view.y
                val x_puchero=binding.imgPutxeroPutxero.x
                val y_puchero=binding.imgPutxeroPutxero.y
                val pucheroanchura=binding.imgPutxeroPutxero.width
                val pucheroaltura=binding.imgPutxeroPutxero.height
                if(y_ingrediente<(y_puchero+pucheroaltura) && y_ingrediente>y_puchero && x_ingrediente<(x_puchero+pucheroanchura) && x_ingrediente>x_puchero){
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
            if(Sharedapp.partida.partida=="guiado"){
                NavFrag.gune++
                BD.actualizar_gune(NavFrag.gune+1,Sharedapp.nombre.nombre.trim())
            }
            SystemClock.sleep(1000)
            mostrarDialogoPersonalizado()
            victoria=true


        }else{
            aciertos++
        }
        return true
    }

    private fun mostrarDialogoPersonalizado(){

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_finalizar,
                DialogInterface.OnClickListener { dialog, id ->
                    moveLefttoRight.cancel()
                    if(NavFrag.modo_libre.size!=0 && Sharedapp.partida.partida =="libre"){
                        if (!NavFrag.modo_libre.contains("7.Gunea".trim())){
                            NavFrag.modo_libre.add("7.Gunea".trim())
                        }
                    }else{
                        NavFrag.modo_libre.add("7.Gunea".trim())
                    }
                    NavFrag.IniciarActivity(requireContext(),"a_mapa")
                    requireActivity().finish()
                    // sign in the user ...
                })
            .setNeutralButton(R.string.repetir,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.MarcadorJuegofin(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Juego1")
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }

    private fun mostrarDialogoDerrota(){

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuegoderrota,null))

            .setNeutralButton(R.string.repetir,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.MarcadorJuegofin(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
                    // sign in the user ...
                    buildSnackbar()
                })
            .setPositiveButton(R.string.volver_mapa,
                DialogInterface.OnClickListener { dialog, id ->
                requireActivity().finish()
                // sign in the user ...
            })
            .setCancelable(false)
            .create()
            .show()

    }

    private fun buildSnackbar() {
        val snackbar = Snackbar.make(
            binding.frameLayout11,
            resources.getString(R.string.saltar_texto),
            BaseTransientBottomBar.LENGTH_LONG
        ).setAction(resources.getString(R.string.saltar)) {
            mediaplayer!!.seekTo(mediaplayer!!.duration)
        }
        snackbar.show()
    }
}