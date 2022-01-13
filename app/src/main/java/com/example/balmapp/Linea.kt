package com.example.balmapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.SystemClock
import android.text.style.LineHeightSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.delay


class Linea @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private val mPaint: Paint
    var startX = 0f
    var startY = 0f
    var endX = 0f
    var endY = 0f
    var rbx=0f
    var rby=0f
    lateinit var button: Button
    lateinit var texto:TextView
    lateinit var txtfin:TextView
    lateinit var layoutInflater: LayoutInflater
    lateinit var activity: FragmentActivity
    lateinit var view: View
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(startX, startY, endX, endY, mPaint)
    }
    var dentro=false
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //val rbxfin=img.width
        //val rbyfin=img.height
        val rbxfin=texto.width
        val rbyfin=texto.height
        if(!dentro){
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Set the end to prevent initial jump (like on the demo recording)
                    endX = event.x
                    endY = event.y
                    invalidate()
                }
                MotionEvent.ACTION_MOVE -> {
                    endX = event.x
                    endY = event.y
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {

                    if (endY<(rby+rbyfin) && endY>rby && endX<(rbx+rbxfin) && endX>rbx){
                        mPaint.color=Color.GREEN
                        NavFrag.terminado_unir=true
                        dentro=true
                        endX=txtfin!!.x 
                        endY=txtfin!!.y + (txtfin!!.height/2)
                        //img.background=resources.getDrawable(R.drawable.borde)
                        //button.isClickable=false
                        texto.isClickable=false
                        NavFrag.contador++
                        if(NavFrag.contador==4){
                            NavFrag.mostrarDialogoPersonalizado(layoutInflater!!,context!!,activity!!,view!!)
                        }
                    }else{
                        //endX = startX+20
                        //endY = startY+20
                        this.isGone=true
                    }

                    invalidate()
                }
            }
        }
        return true
    }

    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.RED
    }

}