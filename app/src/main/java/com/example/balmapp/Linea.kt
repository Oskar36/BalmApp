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
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
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
    var txtfin:TextView?=null
    var scroll: ScrollView?=null
    var txapela=false
    lateinit var layoutInflater: LayoutInflater
    lateinit var activity: FragmentActivity
    lateinit var view: View
    //crea la línea
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
                    // el final de la línea es el mismo que en donde está el dedo
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
                    // en caso de que esté en el rango se pondrá la línea en color verde y la línea se moverá hacia el centro
                    if (endY<(rby+rbyfin) && endY>rby && endX<(rbx+rbxfin) && endX>rbx){
                        mPaint.color=Color.GREEN
                        NavFrag.terminado_unir=true
                        dentro=true
                        if(scroll!=null && txtfin==null){
                            endX=scroll!!.x
                            endY=scroll!!.y + (scroll!!.height/2)
                        }else{
                            endX=txtfin!!.x
                            endY=txtfin!!.y + (txtfin!!.height/2)
                        }
                        texto.isClickable=false
                        //cuando el contador llegue a 0 se terminará el juego
                        NavFrag.contador--
                        if(NavFrag.contador==0){
                            if(scroll!=null && txapela){
                                val fragment: Fragment =f_txapelaunir2()
                                NavFrag.replaceFragment(fragment,activity!!,view!!.id,"Juego1")
                            }else{
                                NavFrag.mostrarDialogoPersonalizado(layoutInflater!!,context!!,activity!!,view!!)
                            }
                        }

                    }else{
                        //En caso de que no esté en rango la línea desaparecerá
                        this.isGone=true
                    }

                    invalidate()
                }
            }
        }
        return true
    }
    //Estilo de la linea
    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.RED
        mPaint.strokeWidth=10f
    }

}