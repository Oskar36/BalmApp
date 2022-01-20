package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.balmapp.databinding.LPartidaBinding
import com.google.firebase.firestore.FirebaseFirestore


private var _binding: LPartidaBinding? = null
private val binding get() = _binding!!
private  lateinit var database: FirebaseFirestore
class f_partida : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //creamos la actividad de cada uno de los botones
        super.onActivityCreated(savedInstanceState)
        NavFrag.pantalla_inicio=false
        //Cuando le demos a modo guiado
        Sharedapp.partida.partida="guiado"
        //Primero insertaremos el apodo
        //Dependiendo si el apodo esta registrado en la BD podremos hacer dos cosas
        //Crear una nueva partida al CLickar en NUEVA PARTIDA
      binding.btnpartidaNueva.setOnClickListener{
          BD.insertarNuevaPartida(Sharedapp.nombre.nombre)
          BD.cargarPartida(Sharedapp.nombre.nombre)
              NavFrag.IniciarActivity(requireContext(),"a_mapa")
              requireActivity().finish()
      }
        //Continuar con una partida ya guardada Clickando en CONTINUAR
        binding.btnpartidaContinuar.setOnClickListener{
            BD.cargarPartida(Sharedapp.nombre.nombre)
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
            requireActivity().finish()
        }


    }
    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LPartidaBinding.inflate(inflater, container, false)
        return  binding.root
    }
}
