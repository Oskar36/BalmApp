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
        super.onActivityCreated(savedInstanceState)
        NavFrag.pantalla_inicio=false
        Sharedapp.partida.partida="guiado"
        if (Sharedapp.usuario.usuario == "Nuevo"){
            binding.btnpartidaContinuar.isVisible=false
        }
      binding.btnpartidaNueva.setOnClickListener{
          Toast.makeText(requireContext(), Sharedapp.usuario.usuario, Toast.LENGTH_SHORT).show()
          BD.insertarNuevaPartida(Sharedapp.nombre.nombre)
              NavFrag.IniciarActivity(requireContext(),"a_mapa")
              requireActivity().finish()
      }
        binding.btnpartidaContinuar.setOnClickListener{
            BD.cargarPartida(Sharedapp.nombre.nombre)
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
            requireActivity().finish()
        }


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LPartidaBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
