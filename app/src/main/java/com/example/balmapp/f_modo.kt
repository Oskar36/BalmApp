package com.example.balmapp


import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.SystemClock
import android.provider.MediaStore
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.balmapp.databinding.LModoBinding
import java.util.*


private var _binding: LModoBinding? = null
private val binding get() = _binding!!
class f_modo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LModoBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NavFrag.pantalla_inicio=true
        binding.btnmodoLibre.setOnClickListener{
            Sharedapp.partida.partida="libre"
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
            requireActivity().finish()
        }
        binding.btnmodoGuiado.setOnClickListener{
            val fragment:Fragment=f_apodo()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
        if(Locale.getDefault().language.equals("es")){
            binding.cartelImg.setBackgroundResource(R.drawable.inicio)
        }else{
            binding.cartelImg.setBackgroundResource(R.drawable.hasiera)
        }

    }
}