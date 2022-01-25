package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LFinBinding



private var _binding: LFinBinding? = null
private val binding get() = _binding!!
class f_fin : Fragment() {

    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LFinBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //si le das al boton siguiente te lleva al mapa
        binding.btnfinSiguiente.setOnClickListener{
                NavFrag.IniciarActivity(requireContext(),"a_mapa")
                requireActivity().finish()
        }
        //si le das al boton repetir te cambia el fragment a el gune en el que esta 
        binding.btnfinRepetir.setOnClickListener{
            val fragment:Fragment=MarcadorJuego(Sharedapp.gune.gune)
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Repetir","Juego")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //carga el fragmente que se le pasa
    private fun MarcadorJuego(gune: String):Fragment{
        var fragment:Fragment?=null
        when (gune){
            "1.Gunea" ->     fragment=f_puente_puzzle()
            "2.Gunea 2" ->   fragment=f_kolitzajuego()
            "3.Gunea 2" ->   fragment=f_jauregi_unirjuego()
            "4.Gunea 2" ->   fragment=f_procesion_ordenar()
            "5.Gunea" ->     fragment=f_juego_txapela_unir()
            "6.Gunea" ->     fragment=f_sanfelipe_cancion()
            "7.Gunea" ->     fragment=f_putxero_juego()
        }
        return fragment!!
    }
}