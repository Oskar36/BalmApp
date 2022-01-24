package com.example.balmapp


import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.balmapp.databinding.LFelipeCancionBinding



private var _binding: LFelipeCancionBinding? = null
private val binding get() = _binding!!
private var mediaPlayer: MediaPlayer?=null
private data class TestErantz(val texto:TextView,val respuesta: String)
private  lateinit var lista:List<TestErantz>
class f_sanfelipe_cancion : Fragment() {
    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LFelipeCancionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val textos = arrayOf( R.string.felipe_cancion_letra1,R.string.felipe_cancion_letra2,R.string.felipe_cancion_letra3,R.string.felipe_cancion_letra4)
        var contador=1
        binding.siguiente.setOnClickListener {
            binding.text1.setTextColor(getResources().getColor(R.color.primaryTextColor))
            binding.text2.setTextColor(getResources().getColor(R.color.primaryTextColor))
            when (contador) {
                1 -> {
                    binding.txtsanfelipeCancion.setText(textos.get(0))
                    if(binding.text1.text.toString().trim().toLowerCase()==("mayo") && binding.text2.text.toString().trim().toLowerCase()==("abril")) {
                        binding.txtsanfelipeCancion.setText(textos.get(1))
                        binding.text2.clearFocus()
                        binding.text1.requestFocus()
                        binding.text1.setText("")
                        binding.text2.setText("")
                        contador++
                    }
                    else{
                        Toast.makeText(requireContext(), R.string.error_toast, Toast.LENGTH_SHORT).show()
                        if(binding.text1.text.toString().trim().toLowerCase()!=("mayo") ){
                            binding.text1.setTextColor(getResources().getColor(R.color.rojo))
                        }
                            if(binding.text2.text.toString().trim().toLowerCase()!=("abril")) {
                                binding.text2.setTextColor(getResources().getColor(R.color.rojo))
                    }
                }
                }

                2 -> { if(binding.text1.text.toString().trim().toLowerCase()==("buena") && binding.text2.text.toString().trim().toLowerCase()==("comer")) {
                    binding.txtsanfelipeCancion.setText(textos.get(2))
                    binding.text2.clearFocus()
                    binding.text1.requestFocus()
                    binding.text1.setText("")
                    binding.text2.setText("")
                    contador++
                }
                else{
                    Toast.makeText(requireContext(), R.string.error_toast, Toast.LENGTH_SHORT).show()
                    if(binding.text1.text.toString().trim().toLowerCase()!=("buena") ){
                        binding.text1.setTextColor(getResources().getColor(R.color.rojo))
                    }
                    if(binding.text2.text.toString().trim().toLowerCase()!=("comer")) {
                        binding.text2.setTextColor(getResources().getColor(R.color.rojo))
                    }
                }

                }
                3 -> {
                    if(binding.text1.text.toString().trim().toLowerCase()==("señorita") && binding.text2.text.toString().trim().toLowerCase()==("peseta")) {
                        binding.txtsanfelipeCancion.setText(textos.get(3))
                        binding.text2.clearFocus()
                        binding.text1.requestFocus()
                        binding.text1.setText("")
                        binding.text2.setText("")
                        contador++
                        binding.siguiente.visibility = INVISIBLE
                        binding.btncorregir.visibility = VISIBLE
                    }
                    else{
                        Toast.makeText(requireContext(), R.string.error_toast, Toast.LENGTH_SHORT).show()
                        if(binding.text1.text.toString().trim().toLowerCase()!=("señorita") ){
                            binding.text1.setTextColor(getResources().getColor(R.color.rojo))
                        }
                        if(binding.text2.text.toString().trim().toLowerCase()!=("peseta")) {
                            binding.text2.setTextColor(getResources().getColor(R.color.rojo))
                        }
                    }

                }

            }
        }



        binding.btncorregir.setOnClickListener {
            binding.text1.setTextColor(getResources().getColor(R.color.primaryTextColor))
            binding.text2.setTextColor(getResources().getColor(R.color.primaryTextColor))
            if(binding.text1.text.toString().trim().toLowerCase()==("mocos") && binding.text2.text.toString().trim().toLowerCase()==("pocos")) {
                if(Sharedapp.partida.partida=="guiado"){
                    NavFrag.gune++
                    BD.actualizar_gune(NavFrag.gune+1,Sharedapp.nombre.nombre.trim())
                }
                if(NavFrag.modo_libre.size!=0 && Sharedapp.partida.partida =="libre"){
                    if (!NavFrag.modo_libre.contains("6.Gunea".trim())){
                        NavFrag.modo_libre.add("6.Gunea".trim())
                    }
                }else{
                    NavFrag.modo_libre.add("6.Gunea".trim())
                }
                mostrarDialogoPersonalizado()
                //Abrir fragment repetir juego
                Sharedapp.gune.gune="6.Gunea"
            }
            else{
                Toast.makeText(requireContext(), R.string.error_toast, Toast.LENGTH_SHORT).show()
                if(binding.text1.text.toString().trim().toLowerCase()!=("mocos") ){
                    binding.text1.setTextColor(resources.getColor(R.color.rojo))
                }
                if(binding.text2.text.toString().trim().toLowerCase()!=("pocos")) {
                    binding.text2.setTextColor(resources.getColor(R.color.rojo))
                }


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
            if(mediaPlayer!!.isPlaying){
                mediaPlayer!!.pause()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
    }
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