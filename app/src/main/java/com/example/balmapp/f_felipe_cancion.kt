package com.example.balmapp


import android.content.DialogInterface
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.balmapp.databinding.LFelipeCancionBinding
import java.util.*


private var _binding: LFelipeCancionBinding? = null
private val binding get() = _binding!!
private var mediaPlayer: MediaPlayer?=null
class f_sanfelipe_cancion : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LFelipeCancionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btncorregir.setOnClickListener{
            Sharedapp.gune.gune="6.Gunea"
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
        }

        //Comprobacion de que las palabras de la cancion esten bien escrita.
        //Cuando las palabras esten mal escritas apareceran en rojo y cuando esten bien saldran en verde.
        binding.btncorregir.setOnClickListener {
            //Palabra 1
            val txt1=ComprobarTextos(binding.txtpalabra1,"mayo")
            //Palabra 2
            val txt2=ComprobarTextos(binding.txtpalabra2,"abril")
            //Palabra 3
            val txt3=ComprobarTextos(binding.txtpalabra3,"santiago")
            //Palabra 4
            val txt4=ComprobarTextos(binding.txtpalabra4,"gil")
            //Palabra 5
            val txt5=ComprobarTextos(binding.txtpalabra5,"buena")
            //Palabra 6
            val txt6=ComprobarTextos(binding.txtpalabra6,"comer")
            //Palabra 7
            val txt7=ComprobarTextos(binding.txtpalabra7,"felipe")
            //Palabra 8
            val txt8=ComprobarTextos(binding.txtpalabra8,"san")
            //Palabra 9
            val txt9=ComprobarTextos(binding.txtpalabra9,"señorita")
            //Palabra 10
            val txt10=ComprobarTextos(binding.txtpalabra10,"peseta")
            //Palabra 11
            val txt11=ComprobarTextos(binding.txtpalabra11,"flores")
            //Palabra 12
            val txt12=ComprobarTextos(binding.txtpalabra12,"flores")
            //Palabra 13
            val txt13=ComprobarTextos(binding.txtpalabra13,"mocos")
            //Palabra 14
            val txt14=ComprobarTextos(binding.txtpalabra14,"pocos")
            //Palabra 15
            val txt15=ComprobarTextos(binding.txtpalabra15,"viva")
            //Palabra 16
            val txt16=ComprobarTextos(binding.txtpalabra16,"viva")
            val Listatxt= listOf(txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,txt12,txt13,txt14,txt15,txt16)
            if(ComprobarTodos(Listatxt)){


                mostrarDialogoPersonalizado()
                //Abrir fragment repetir juego
                Sharedapp.gune.gune="6.Gunea"

            }else{
                Toast.makeText(requireContext(), R.string.correccion_toast, Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onResume() {
        super.onResume()
         mediaPlayer = MediaPlayer.create(context, R.raw.felipe_cancion)
       binding.play.setOnClickListener{
           if(mediaPlayer!!.isPlaying){
               mediaPlayer!!.seekTo(0)
           } else {
               mediaPlayer!!.start()
           }
       }
        binding.pause.setOnClickListener{
            mediaPlayer!!.pause()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
    }
    private fun ComprobarTextos(txt:TextView, respuesta:String):Boolean{
        return if(txt.text.toString().lowercase(Locale.getDefault()) == respuesta){
            txt.setTextColor(Color.parseColor("#00ff00"))
            true
        }else{
            txt.setTextColor(Color.parseColor("#ad0a15"))
            false
        }
    }
    private fun ComprobarTodos(txtL:List<Boolean>):Boolean{
        txtL.forEach{
            if (!it){
                return false
            }
        }
        return true
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