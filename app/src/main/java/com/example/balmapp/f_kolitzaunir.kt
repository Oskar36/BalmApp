package com.example.balmapp


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.example.balmapp.databinding.LKolitzaunirBinding


private var _binding: LKolitzaunirBinding? = null
private var linea: Linea? =null
// This property is only valid between onCreateView and
// onDestroyView.

private val binding get() = _binding!!
class f_kolitzajuego : Fragment() {
    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LKolitzaunirBinding.inflate(inflater, container, false)

        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NavFrag.contador=6
        NavFrag.atras_lugar="Juego2"
        Sharedapp.gune.gune="2.Gunea 2"


        // esto es para decir cual es el inicio y el final de la linea
        binding.imgtxapelaJuegoImg1.setOnClickListener{
            crearLinea(binding.imgtxapelaJuegoImg1,binding.txtTextoPentekoste)
        }
        binding.imgtxapelaJuegoImg3.setOnClickListener{
            crearLinea(binding.imgtxapelaJuegoImg3,binding.txtTextoTontorra)
        }
        binding.imgtxapelaJuegoImg2.setOnClickListener{
            crearLinea(binding.imgtxapelaJuegoImg2,binding.txtTextoPazkoa)
        }
        binding.imgtxapelaJuegoImg6.setOnClickListener{
            crearLinea(binding.imgtxapelaJuegoImg6,binding.txtTextoErromeria)
        }
        binding.imgtxapelaJuegoImg5.setOnClickListener{
            crearLinea(binding.imgtxapelaJuegoImg5,binding.txtTextoErmita)
        }
        binding.imgtxapelaJuegoImg4.setOnClickListener{
            crearLinea(binding.imgtxapelaJuegoImg4,binding.txtTextoBazkaria)
        }
    }

    //con esta funcion se crea la linea dependiendo de la x y de inicio y del final
    private fun crearLinea(txtinicion: TextView, textfin: TextView){
        if(linea!=null && !NavFrag.terminado_unir){
            linea!!.isGone=true
        }
        linea=Linea(requireContext())
        binding.frameLayout7.addView(linea)
        linea!!.startX=txtinicion.x + txtinicion.width
        linea!!.startY=txtinicion.y + (txtinicion.height/2)
        linea!!.endX=txtinicion.x + txtinicion.width+30
        linea!!.endY=txtinicion.y + (txtinicion.height/2)
        linea!!.rbx=textfin.x
        linea!!.rby=textfin.y
        linea!!.txtfin=textfin
        linea!!.texto=txtinicion!!
        linea!!.layoutInflater=layoutInflater
        linea!!.activity=requireActivity()
        linea!!.view=((view as ViewGroup).parent as View)
        NavFrag.terminado_unir=false
    }



// dialogo personalizado si le das a positivebutton te lleva al mapa y si le das a al neurtral button te repite el juego
private fun mostrarDialogoPersonalizado(){

AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
    .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
    .setPositiveButton(R.string.txt_finalizar,
        DialogInterface.OnClickListener { dialog, id ->
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
            requireActivity().finish()
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

