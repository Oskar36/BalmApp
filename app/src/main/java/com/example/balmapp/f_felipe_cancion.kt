package com.example.balmapp

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.balmapp.databinding.LFelipeCancionBinding


private var _binding: LFelipeCancionBinding? = null
private val binding get() = _binding!!
private var mediaPlayer: MediaPlayer?=null
class f_sanfelipe_cancion : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LFelipeCancionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btncorregir.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")

        }

        //Comprobacion de que las palabras de la cancion esten bien escrita.
        //Cuando las palabras esten mal escritas apareceran en rojo y cuando esten bien saldran en verde.
        binding.btncorregir.setOnClickListener {
            //Palabra 1
            if(binding.txtpalabra1.text.toString().toLowerCase() != ("mayo")){
                binding.txtpalabra1.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra1.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 2
            if(binding.txtpalabra2.text.toString().toLowerCase() != ("abril")){
                binding.txtpalabra2.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra2.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 3
            if(binding.txtpalabra3.text.toString().toLowerCase() != ("santiago")){
                binding.txtpalabra3.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra3.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 4
            if(binding.txtpalabra4.text.toString().toLowerCase() != ("gil")){
                binding.txtpalabra4.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra4.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 5
            if(binding.txtpalabra5.text.toString().toLowerCase() != ("buena")){
                binding.txtpalabra5.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra5.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 6
            if(binding.txtpalabra6.text.toString().toLowerCase() != ("comer")){
                binding.txtpalabra6.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra6.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 7
            if(binding.txtpalabra7.text.toString().toLowerCase() != ("felipe")){
                binding.txtpalabra7.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra7.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 8
            if(binding.txtpalabra8.text.toString().toLowerCase() != ("san")){
                binding.txtpalabra8.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra8.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 9
            if(binding.txtpalabra9.text.toString().toLowerCase() != ("señorita")){
                binding.txtpalabra9.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra9.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 10
            if(binding.txtpalabra10.text.toString().toLowerCase() != ("peseta")){
                binding.txtpalabra10.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra10.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 11
            if(binding.txtpalabra11.text.toString().toLowerCase() != ("flores")){
                binding.txtpalabra11.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra11.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 12
            if(binding.txtpalabra12.text.toString().toLowerCase() != ("flores")){
                binding.txtpalabra12.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra12.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 13
            if(binding.txtpalabra13.text.toString().toLowerCase() != ("mocos")){
                binding.txtpalabra13.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra13.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 14
            if(binding.txtpalabra14.text.toString().toLowerCase() != ("pocos")){
                binding.txtpalabra14.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra14.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 15
            if(binding.txtpalabra15.text.toString().toLowerCase() != ("viva")){
                binding.txtpalabra15.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra15.setTextColor(Color.parseColor("#00ff00"))
            }

            //Palabra 16
            if(binding.txtpalabra16.text.toString().toLowerCase() != ("viva")){
                binding.txtpalabra16.setTextColor(Color.parseColor("#ad0a15"))
            }
            else{
                binding.txtpalabra16.setTextColor(Color.parseColor("#00ff00"))
            }



            //Abrir fragment repetir juego
            Sharedapp.gune.gune="6.Gunea"
            val fragment:Fragment=f_fin()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }







    }
    override fun onResume() {
        super.onResume()
         mediaPlayer = MediaPlayer.create(context, R.raw.felipe_cancion)
       binding.play.setOnClickListener{
           if(mediaPlayer!!.isPlaying()){
               mediaPlayer!!.seekTo(0);
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
}