package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LPutxeroJuegoBinding
import android.view.animation.TranslateAnimation
import androidx.constraintlayout.widget.ConstraintLayout


private var _binding: LPutxeroJuegoBinding? = null
private val binding get() = _binding!!

class f_putxero_juego : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LPutxeroJuegoBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnPutxerojuego.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
        }
        val moveLefttoRight = TranslateAnimation(550F, -470F, 0F, 0F)
        moveLefttoRight.setDuration(30000)
        moveLefttoRight.setFillAfter(true)
        binding.imgPutxeroTren.startAnimation(moveLefttoRight)
        binding.imgPutxeroAlimento1.setOnTouchListener(touchListener)
        binding.imgPutxeroAlimento5.setOnTouchListener(touchListener)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    var xDelta: Int=0
    var yDelta: Int=0
    val touchListener = View.OnTouchListener { view, event ->
        val x = event.rawX.toInt()
        val y = event.rawY.toInt()
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val lParams = view.layoutParams as ConstraintLayout.LayoutParams
                xDelta = x - lParams.leftMargin
                yDelta = y - lParams.topMargin
            }
            MotionEvent.ACTION_MOVE -> {
                val layoutParams = view
                    .layoutParams as ConstraintLayout.LayoutParams
                layoutParams.leftMargin = x - xDelta
                layoutParams.topMargin = y - yDelta
                layoutParams.rightMargin = 0
                layoutParams.bottomMargin = 0
                view.layoutParams = layoutParams
            }
        }
       binding.frameLayout11.invalidate()
        true
    }
}