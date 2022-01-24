package com.example.balmapp



import android.content.DialogInterface
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
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LModoBinding.inflate(inflater, container, false)
        return  binding.root
    }

    //Aqui seleccionaremos el metodo de juego que queramos juegar
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NavFrag.pantalla_inicio=true
        //El boton MODO LIBRE nos llevara al mapa donde todos los pines estaran disponible para juegar
        //La unica condicion sera a estar a 50m como mucho del punto
        binding.btnmodoLibre.setOnClickListener{
            Sharedapp.partida.partida="libre"
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
            requireActivity().finish()
        }

        //El boton MODO GUIADO nos llevara al mapa con los pines desactivados menos uno.
        //EL modo guiado nos llevara por una ruta predeterminada
        //Dependiendo de si ya tenemos una partida iniciado puede llegar a haber pines verdes, significa que ya han sido completados.
        binding.btnmodoGuiado.setOnClickListener{
            val fragment:Fragment=f_apodo()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
        //Este bucle lo que hace es comprobar el idioma del telefono cada vez que entramos en la aplicacion.
        //Dependiendo del idioma la imagen del tablero cambiara junto al idioma
        if(Locale.getDefault().language.equals("es")){
            binding.cartelImg.setBackgroundResource(R.drawable.inicio)
        }else{
            binding.cartelImg.setBackgroundResource(R.drawable.hasiera)
        }

//cuando le das click en el texto sale un dialogo con la informacion
        binding.info.setOnClickListener {
            mostrarDialogoPersonalizado()
        }
    }
    private fun mostrarDialogoPersonalizado(){


        androidx.appcompat.app.AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_info,null))
            .setPositiveButton(R.string.txt_cancelar,
                DialogInterface.OnClickListener { dialog, id ->
                
                })

            .setCancelable(false)
            .create()
            .show()


    }
}