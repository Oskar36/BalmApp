package com.example.balmapp

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.balmapp.databinding.LKolitzaunirBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore


private var _binding: LKolitzaunirBinding? = null

// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!
class f_kolitzajuego : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LKolitzaunirBinding.inflate(inflater, container, false)

        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizarKolitza.setOnClickListener{
            mostrarDialogoPersonalizado()
            Sharedapp.gune.gune="2.Gunea 2"
           /* val fragment:Fragment=f_fin()
            NavFrag.replaceFragmen
            t(fragment,requireActivity(),((view as ViewGroup).parent as View).id)*/
        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



private fun mostrarDialogoPersonalizado(){

AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
    .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
    .setPositiveButton(R.string.txt_finalizar,
        DialogInterface.OnClickListener { dialog, id ->
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
            // sign in the user ...
        })
    .setNeutralButton(R.string.repetir,
        DialogInterface.OnClickListener { dialog, id ->
            val fragment:Fragment=NavFrag.MarcadorJuegofin(Sharedapp.gune.gune)
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
            // sign in the user ...
        })
    .setCancelable(false)
    .create()
    .show()

}


}

