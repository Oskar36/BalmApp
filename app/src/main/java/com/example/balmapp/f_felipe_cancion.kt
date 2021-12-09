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
private data class TestErantz(val texto:TextView,val respuesta: String)
private  lateinit var lista:List<TestErantz>
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
        lista= listOf(TestErantz(binding.txtpalabra1,"mayo"),TestErantz(binding.txtpalabra2,"abril"),TestErantz(binding.txtpalabra3,"santiago"),TestErantz(binding.txtpalabra4,"gil"),TestErantz(binding.txtpalabra5,"buena"),TestErantz(binding.txtpalabra6,"comer"),TestErantz(binding.txtpalabra7,"felipe"),TestErantz(binding.txtpalabra8,"san"),TestErantz(binding.txtpalabra9,"señorita"),TestErantz(binding.txtpalabra10,"peseta"),TestErantz(binding.txtpalabra11,"flores"),TestErantz(binding.txtpalabra12,"flores"),TestErantz(binding.txtpalabra13,"mocos"),TestErantz(binding.txtpalabra14,"pocos"),TestErantz(binding.txtpalabra15,"viva"),TestErantz(binding.txtpalabra16,"viva"))
        binding.btncorregir.setOnClickListener {
            if(Compr()){
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
    private fun Compr():Boolean{
        var aciertos=0
        lista.forEach{
            if(it.texto.text.isNotEmpty()){
                if (it.texto.text.toString().lowercase().trim()==it.respuesta){
                    it.texto.setTextColor(Color.parseColor("#00ff00"))
                    aciertos++
                }else{
                    it.texto.setTextColor(Color.parseColor("#ad0a15"))
                }
            }

        }
        return aciertos== lista.size
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