package com.example.balmapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
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


                // comp_puzzle_pieza()

                when{
                    //11
                    ((x<=217&& x>=-93) && (y<=675 && (y>=365)))-> {
                        if(resources.getResourceEntryName(view.id).equals("puzzle_11")){
                            binding.bien11.isVisible =true
                            binding.puzzle11.isVisible =false
                            contador++

                            if (contador==9){
                                mostrarDialogoPersonalizado()}

                        }
                        else{
                            view?.x=x0
                            view?.y=y0
                            y0=0.0f
                            x0=0.0f
                        }
                    }
                    //12
                    ((x<=526&& x>=217) && (y<=675 && (y>=365)))->{

                        if(resources.getResourceEntryName(view.id).equals("puzzle_12")){
                            binding.bien12.isVisible =true
                            binding.puzzle12.isVisible =false
                            contador++
                            if (contador==9){
                                mostrarDialogoPersonalizado()}
                        }
                        else{
                            view?.x=x0
                            view?.y=y0
                            y0=0.0f
                            x0=0.0f
                        }
                    }
                    //13
                    ((x<=835&& x>=526) && (y<=675 && (y>=365)))->{

                        if(resources.getResourceEntryName(view.id).equals("puzzle_13")){
                            binding.bien13.isVisible =true
                            binding.puzzle13.isVisible =false
                            contador++
                            if (contador==9){

                                mostrarDialogoPersonalizado()}
                        }
                        else{
                            view?.x=x0
                            view?.y=y0
                            y0=0.0f
                            x0=0.0f
                        }
                    }
                    //21
                    ((x<=217&& x>=-93) && (y<=1021 && (y>=675)))->{

                        if(resources.getResourceEntryName(view.id).equals("puzzle_21")){
                            binding.bien21.isVisible =true
                            binding.puzzle21.isVisible =false
                            contador++
                            if (contador==9){
                                mostrarDialogoPersonalizado()}
                        }
                        else{
                            view?.x=x0
                            view?.y=y0
                            y0=0.0f
                            x0=0.0f
                        }
                    }
                    //22
                    ((x<=526&& x>=217) && (y<=1021 && (y>=675)))->{

                        if(resources.getResourceEntryName(view.id).equals("puzzle_22")){
                            binding.bien22.isVisible =true
                            binding.puzzle22.isVisible =false
                            contador++
                            if (contador==9){
                                mostrarDialogoPersonalizado()}
                        }
                        else{
                            view?.x=x0
                            view?.y=y0
                            y0=0.0f
                            x0=0.0f
                        }
                    }
                    //23
                    ((x<=835&& x>=526) && (y<=1021 && (y>=675)))->{

                        if(resources.getResourceEntryName(view.id).equals("puzzle_23")){
                            binding.bien23.isVisible =true
                            binding.puzzle23.isVisible =false
                            contador++
                            if (contador==9){
                                mostrarDialogoPersonalizado()}
                        }
                        else{
                            view?.x=x0
                            view?.y=y0
                            y0=0.0f
                            x0=0.0f
                        }
                    }
                    //31
                    ((x<=217&& x>=-93) && (y<=1369 && (y>=1021)))->{

                        if(resources.getResourceEntryName(view.id).equals("puzzle_31")){
                            binding.bien31.isVisible =true
                            binding.puzzle31.isVisible =false
                            contador++
                            if (contador==9){
                                mostrarDialogoPersonalizado()}
                        }
                        else{
                            view?.x=x0
                            view?.y=y0
                            y0=0.0f
                            x0=0.0f
                        }
                    }
                    //32
                    ((x<=526&& x>=217) && (y<=1369 && (y>=1021)))->{

                        if(resources.getResourceEntryName(view.id).equals("puzzle_32")){
                            binding.bien32.isVisible =true
                            binding.puzzle32.isVisible =false
                            contador++
                            if (contador==9){
                                mostrarDialogoPersonalizado()}
                        }
                        else{
                            view?.x=x0
                            view?.y=y0
                            y0=0.0f
                            x0=0.0f
                        }
                    }
                    //33
                    ((x<=835&& x>=526) && (y<=1369 && (y>=1021)))->{

                        if(resources.getResourceEntryName(view.id).equals("puzzle_33")){
                            binding.bien33.isVisible =true
                            binding.puzzle33.isVisible =false
                            contador++
                            if (contador==9){
                                mostrarDialogoPersonalizado()}
                        }
                        else{
                            view?.x=x0
                            view?.y=y0
                            y0=0.0f
                            x0=0.0f
                        }
                    }


                }

            }

        }
        binding.frameLayout7.invalidate()
        true
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


    fun comp_puzzle_pieza() {
        val location = IntArray(2)
        view?.getLocationOnScreen(location)
        val x = location[0]
        val y = location[1]
        val location2 = IntArray(2)
        binding.puzzle.getLocationOnScreen(location2)
        val x2 = location2[0]
        val y2 = location2[1]

    }

    private fun mostrarDialogoPersonalizado(){
        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_finalizar,
                DialogInterface.OnClickListener { dialog, id ->
                    NavFrag.IniciarActivity(requireContext(),"a_mapa")
                    // sign in the user ...
                })
            .setNeutralButton(R.string.repetir,
                DialogInterface.OnClickListener { dialog, id ->
                    Sharedapp.gune.gune="1.Gunea"
                    val fragment:Fragment=NavFrag.MarcadorJuegofin(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }


}