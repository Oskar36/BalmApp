package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LProcesionExplicacionBinding
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.SystemClock
import android.widget.ImageView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var animacion : AnimationDrawable
/**
 * A simple [Fragment] subclass.
 * Use the [f_procesion_explicacion.newInstance] factory method to
 * create an instance of this fragment.
 */

private var _binding: LProcesionExplicacionBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
class f_procesion_explicacion : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)



        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LProcesionExplicacionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnprocesionExplicacionJugar.setOnClickListener(){
            val fragment:Fragment=f_procesion_juego_adivinar_Jesus()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
            //paramos el audio
            mediaplayer!!.stop()

        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imgprocesionExplicacionLogo)


        NavFrag.animacion_dantzaris(binding.imgprocesionExplicacionLogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_precesion)
<<<<<<< HEAD
=======
        //Iniciamos el audio
        mediaplayer!!.start()

        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imgprocesionExplicacionLogo)        }
    }

    override fun onStart() {
        super.onStart()

>>>>>>> 35f357f8996e877df11b071a9ee236fad1681f6b

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment f_procesion_explicacion.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            f_procesion_explicacion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

    override fun onDestroy() {
        super.onDestroy()
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
        mediaPlayer?.release()
        mediaplayer = null


    }
}