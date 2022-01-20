package com.example.balmapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.balmapp.databinding.LPuentePuzzleBinding
import java.time.Clock

private var _binding: LPuentePuzzleBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
private var contador=0
class f_puente_puzzle : Fragment() {
    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LPuentePuzzleBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        contador=0
        super.onActivityCreated(savedInstanceState)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_zubia)
        //Iniciamos el audio
        mediaplayer!!.start()
        binding.puzzle11.setOnTouchListener(touchListener)
        binding.puzzle12.setOnTouchListener(touchListener)
        binding.puzzle13.setOnTouchListener(touchListener)
        binding.puzzle21.setOnTouchListener(touchListener)
        binding.puzzle22.setOnTouchListener(touchListener)
        binding.puzzle23.setOnTouchListener(touchListener)
        binding.puzzle31.setOnTouchListener(touchListener)
        binding.puzzle32.setOnTouchListener(touchListener)
        binding.puzzle33.setOnTouchListener(touchListener)
    }
    private var yDelta: Int = 0
    private var xDelta: Int = 0
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

            //para mover las imagenes
            MotionEvent.ACTION_MOVE -> {
                val layoutParams = view
                    .layoutParams as ConstraintLayout.LayoutParams
                layoutParams.leftMargin = x - xDelta
                layoutParams.topMargin = y - yDelta
                layoutParams.rightMargin = 0
                layoutParams.bottomMargin = 0
                view.layoutParams = layoutParams
            }

            //cuando sueltes
            MotionEvent.ACTION_UP -> {

                var bien: ImageView? =null
                when(resources.getResourceEntryName(view.id)){
                    "puzzle_11"-> bien=binding.bien11
                    "puzzle_12"-> bien=binding.bien12
                    "puzzle_13"-> bien=binding.bien13
                    "puzzle_21"-> bien=binding.bien21
                    "puzzle_22"-> bien=binding.bien22
                    "puzzle_23"-> bien=binding.bien23
                    "puzzle_31"-> bien=binding.bien31
                    "puzzle_32"-> bien=binding.bien32
                    "puzzle_33"-> bien=binding.bien33
                }
                comp_puzzle_pieza(bien!!,view,x0,y0)
            }
        }
        binding.frameLayout7.invalidate()
        true
    }
    override fun onStop() {
        super.onStop()
        //liberacion del productor de medios
        mediaplayer?.release()
        mediaplayer = null


    }


    fun comp_puzzle_pieza(bien:ImageView,pieza:View, x1:Float,y1:Float) {
        val bienanchura=bien.width
        val bienaltura=bien.height
        val piezaanchura=pieza.width
        val piezaaltura=pieza.height
        val location2 = IntArray(2)
        bien!!.getLocationOnScreen(location2)
        val bienx = location2[0]
        val bieny = location2[1]
        val location = IntArray(2)
        pieza!!.getLocationOnScreen(location)
        val piezax = location[0]
        val piezay = location[1]
        if((piezay<(bieny+bienaltura) && piezay>bieny && piezax<(bienx+bienanchura) && piezax>bienx)|| ((piezay+piezaaltura)<(bieny+bienaltura) && (piezay+piezaaltura)>bieny && (piezax+piezaanchura)<(bienx+bienanchura) && (piezax+piezaanchura)>bienx)){
            bien.isVisible =true
            pieza.isVisible =false
            contador++
            if (contador==9){
                descargar()
                if(Sharedapp.partida.partida=="guiado"){
                    NavFrag.gune++
                    BD.actualizar_gune(NavFrag.gune+1,Sharedapp.nombre.nombre.trim())
                }
            }
        }else{
            pieza.x=x1
            pieza.y=y1
            x0=0.0f
            y0=0.0f
        }
    }

    private fun mostrarDialogoPersonalizado(){
        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_finalizar,
                DialogInterface.OnClickListener { dialog, id ->
                    NavFrag.IniciarActivity(requireContext(),"a_mapa")
                    requireActivity().finish()
                    // sign in the user ...
                })
            .setNeutralButton(R.string.repetir,
                DialogInterface.OnClickListener { dialog, id ->
                    Sharedapp.gune.gune="1.Gunea"
                    val fragment:Fragment=NavFrag.MarcadorJuegofin(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Juego1")
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }
    private fun descargar(){
        val bm = BitmapFactory.decodeResource(resources, R.drawable.puente_puzzle_img)

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogo_descargar,null))
            .setPositiveButton(R.string.si,
                DialogInterface.OnClickListener { dialog, id ->


                    MediaStore.Images.Media.insertImage(requireActivity().contentResolver,bm, "Puzzle" , "Puzzle puente")
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