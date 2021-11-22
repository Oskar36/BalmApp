package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LFinIntermedioBinding

private var _binding:LFinIntermedioBinding?=null
private val binding get() = _binding!!
class f_fin_intermedio : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding=LFinIntermedioBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinSiguiente.setOnClickListener{
            val fragment:Fragment=AbrirSiguiente(Sharedapp.gune.gune)
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Unir")
        }
        binding.btnfinRepetir.setOnClickListener{
            val fragment:Fragment=MarcadorJuego(Sharedapp.gune.gune)
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Repetir","SopaLetras")
        }
    }

    private fun AbrirSiguiente(gune: String):Fragment {
        var fragment:Fragment?=null
        when (gune){
            "2.Gunea 1" ->   fragment=f_kolitzajuego()
            "3.Gunea 1" ->   fragment=f_jauregi_unirjuego()
            "4.Gunea 1" ->   fragment=f_procesion_ordenar()
            "2.Gunea 2" ->   fragment=f_kolitzajuego()
            "3.Gunea 2" ->   fragment=f_procesion_ordenar()
            "4.Gunea 2" ->   fragment=f_procesion_ordenar()
        }
        return fragment!!
    }
    private fun MarcadorJuego(gune: String):Fragment{
        var fragment:Fragment?=null
        when (gune){
            "2.Gunea 1" ->   fragment=f_kolitza_juego_sopaletras()
            "3.Gunea 1" ->   fragment=f_jauregi_puzzle()
            "4.Gunea 1" ->   fragment=f_procesion_juego_adivinar_Jesus()
            "2.Gunea 2" ->   fragment=f_kolitza_juego_sopaletras()
            "3.Gunea 2" ->   fragment=f_jauregi_puzzle()
            "4.Gunea 2" ->   fragment=f_procesion_juego_adivinar_Jesus()
        }
        return fragment!!
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}