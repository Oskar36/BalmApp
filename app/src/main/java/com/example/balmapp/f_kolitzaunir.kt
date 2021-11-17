package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LApodoBinding
import com.example.balmapp.databinding.LKolitzaunirBinding


private var _binding: LKolitzaunirBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!
class f_kolitzajuego : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LKolitzaunirBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizarKolitza.setOnClickListener(){
            Sharedapp.gune.gune="2.Gunea 2"
            val fragment:Fragment=f_fin()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}