package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LApodoBinding
import com.example.balmapp.databinding.LJauregiUnirBinding


private var _binding: LJauregiUnirBinding? = null
private val binding get() = _binding!!

class f_jauregi_unirjuego : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LJauregiUnirBinding.inflate(inflater, container, false)
        return  binding.root
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)


        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imglogo)        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizarjauregi.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}