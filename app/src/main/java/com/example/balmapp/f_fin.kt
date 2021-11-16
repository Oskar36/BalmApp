package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.balmapp.databinding.LApodoBinding
import com.example.balmapp.databinding.LFinBinding



private var _binding: LFinBinding? = null
private val binding get() = _binding!!

class f_fin : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LFinBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toast.makeText(requireContext(), "${Sharedapp.gune.gune}", Toast.LENGTH_SHORT).show()
        binding.btnfinContinuar.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
        }
        binding.btnfinRepetir.setOnClickListener(){
            val fragment:Fragment=MarcadorJuego(Sharedapp.gune.gune)
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
            Sharedapp.gune.gune=""
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun MarcadorJuego(gune: String):Fragment{
        var fragment:Fragment?=null
        when (gune){
            "1.Gunea" ->     fragment=f_puente_puzzle()
            "2.Gunea 1" ->   fragment=f_kolitza_juego_sopaletras()
            "2.Gunea 2" ->   fragment=f_kolitzajuego()
            "3.Gunea 1" ->   fragment=f_jauregi_puzzle()
            "3.Gunea 2" ->   fragment=f_jauregi_unirjuego()
            "4.Gunea" ->     fragment=f_procesion_ordenar()
            "5.Gunea" ->     fragment=f_juego_txapela_unir()
            "6.Gunea" ->     fragment=f_sanfelipe_cancion()
            "7.Gunea" ->     fragment=f_putxero_juego()
        }
        return fragment!!
    }
}