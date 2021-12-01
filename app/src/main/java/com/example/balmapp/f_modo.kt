package com.example.balmapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.btnmodoLibre.setOnClickListener{
            Sharedapp.partida.partida=""
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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}