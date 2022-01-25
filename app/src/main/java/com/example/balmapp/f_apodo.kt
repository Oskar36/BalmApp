package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.balmapp.databinding.LApodoBinding
import com.google.firebase.firestore.FirebaseFirestore

private var _binding: LApodoBinding? = null
private val binding get() = _binding!!
private  lateinit var database: FirebaseFirestore
class f_apodo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LApodoBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onResume() {
        super.onResume()
        NavFrag.pantalla_inicio=false
        database = FirebaseFirestore.getInstance()

        //este boton comprueba si existe un usuario creado o no
        binding.btnapodoJugar.setOnClickListener(){
            // si se ha rellenado el texto para poner usuario se guarda
            if(binding.txtapodoApodo.text.toString().isNotEmpty()){
                Sharedapp.nombre.nombre=binding.txtapodoApodo.text.toString().toLowerCase()
            var doc=database.collection("apodos")
                .document(binding.txtapodoApodo.text.toString().trim().toLowerCase())
            doc.get()
                .addOnSuccessListener { document ->
                    // si el usuario existe se cambiea el fragment a partida si no te lleva directamente al mapa
                    if(document.exists()){
                        val fragment:Fragment=f_partida()
                        NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
                    }else{
                        BD.insertarApodo(binding.txtapodoApodo.text.toString().toLowerCase())
                        Sharedapp.partida.partida="guiado"
                        NavFrag.IniciarActivity(requireContext(),"a_mapa")
                        requireActivity().finish()
                    }
                }
                .addOnFailureListener(){
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
            //si no introduces nada en el texto de usuario salta un mensaje de error
            else{
                Toast.makeText(requireContext(), R.string.camposvacios, Toast.LENGTH_SHORT).show()
            }
        }
    }
}