package com.example.balmapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.balmapp.databinding.LKolitzaSopaletrasBinding
import java.util.*
import kotlin.concurrent.schedule

private var _binding: LKolitzaSopaletrasBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
private var xInitial = -1f
private var yInitial = -1f

private var xDiff = -1f
private var yDiff = -1f

private var prevXDiff = -1f
private var prevYDiff = -1f

enum class SwipeState { Undefined, Vertical, Horizontal }
private var swipeState = SwipeState.Undefined

private var cellWidth = 0
class f_kolitza_juego_sopaletras : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LKolitzaSopaletrasBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cellWidth = resources.displayMetrics.widthPixels/10
        for (i in 0 until numWords){
            wordArray[i] = Letra(words[i])
        }
        val childCount = binding.wordsGrid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = binding.wordsGrid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                linearLayout.getChildAt(t).setOnTouchListener(touchListener)
            }
        }

        // Adjusting height fo the grid
        val params = binding.wordsGrid.layoutParams as ConstraintLayout.LayoutParams
        params.height = resources.displayMetrics.widthPixels
        binding.wordsGrid.layoutParams = params
        generateRandomLetters()
        binding.btnsiguienteJuego.setOnClickListener{
            //paramos el audio
            mediaplayer!!.stop()
            Sharedapp.gune.gune="2.Gunea 1"
            mostrarDialogoPersonalizado()
           /* val fragment:Fragment=f_fin_intermedio()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)*/


        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_kolitza)


        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imglogo)        }

        //parar y continuar el audio
        binding.imglogo.setOnClickListener {
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imglogo)
                mediaplayer!!.pause()
            }else{
                if(mediaplayer!!.currentPosition!=0 && mediaplayer!!.currentPosition!= mediaplayer!!.duration){
                    mediaplayer!!.seekTo(mediaplayer!!.currentPosition)
                }
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.imglogo)
            }
        }
    }
    override fun onStart() {
        super.onStart()
        //Iniciamos el audio
        mediaplayer!!.start()
    }
    override fun onStop() {
        super.onStop()
        //liberacion del productor de medios
        mediaplayer?.release()
        mediaplayer = null
    }
    private fun mostrarDialogoPersonalizado(){

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_siguientejuego,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.AbrirSiguiente(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Juego1")
                    // sign in the user ...
                })
            .setNeutralButton(R.string.repetir,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.MarcadorJuegofinintermedio(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }
    @SuppressLint("ClickableViewAccessibility")
    val touchListener = View.OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // User started selecting cells
                v.background = ContextCompat.getDrawable(requireContext(), R.drawable.borde)
                xInitial = event.x
                yInitial = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                // User's still selecting cells
                if(xInitial != -1f && yInitial != -1f){

                    val tag = v.tag.toString()
                    val tagInt = tag.toInt()

                    xDiff = xInitial - event.x

                    yDiff = yInitial - event.y

                    if(swipeState == SwipeState.Undefined || swipeState == SwipeState.Horizontal){
                        when {
                            xDiff > cellWidth -> {
                                // moving left
                                if(prevXDiff == -1f || prevXDiff != -1f && prevXDiff < xDiff){
                                    selectSingleCell((tagInt - (xDiff / cellWidth).toInt()).toString())
                                    swipeState = SwipeState.Horizontal
                                } else if ( prevXDiff != -1f && prevXDiff > xDiff){
                                    unselectSingleCell((tagInt - (prevXDiff / cellWidth).toInt()).toString())
                                }
                            }
                            (-1) * xDiff > cellWidth -> {
                                // moving right
                                if(prevXDiff == -1f || prevXDiff != -1f && prevXDiff > xDiff){
                                    selectSingleCell((tagInt + -1 * (xDiff / cellWidth).toInt()).toString())
                                    swipeState = SwipeState.Horizontal
                                } else if ( prevXDiff != -1f && prevXDiff < xDiff){
                                    unselectSingleCell((tagInt - (prevXDiff / cellWidth).toInt()).toString())
                                }
                            }
                        }
                    }

                    if(swipeState == SwipeState.Undefined || swipeState == SwipeState.Vertical){
                        when {
                            yDiff > cellWidth -> {
                                // moving up
                                if(prevYDiff == -1f || prevYDiff != -1f && prevYDiff < yDiff){
                                    selectSingleCell((tagInt - 10*(yDiff/cellWidth).toInt()).toString())
                                    swipeState = SwipeState.Vertical
                                } else if (prevYDiff != -1f && prevYDiff > yDiff){
                                    unselectSingleCell((tagInt - 10*(yDiff/cellWidth).toInt()).toString())
                                }
                            }
                            (-1)*yDiff > cellWidth -> {
                                // moving down
                                if(prevYDiff == -1f || prevYDiff != -1f && prevYDiff > yDiff){
                                    selectSingleCell((tagInt + -10*(yDiff/cellWidth).toInt()).toString())
                                    swipeState = SwipeState.Vertical
                                } else if (prevYDiff != -1f && prevYDiff < yDiff){
                                    unselectSingleCell((tagInt - 10*(yDiff/cellWidth).toInt()).toString())
                                }
                            }
                        }
                    }
                    prevXDiff = xDiff
                    prevYDiff = yDiff
                }
            }

            MotionEvent.ACTION_UP -> {
                // User's done selecting cells
                val tag = v.tag.toString()
                val tagInt = tag.toInt()
                var finalTag = tag

                if(swipeState == SwipeState.Horizontal){
                    finalTag = when {
                        xDiff > cellWidth -> {
                            (tagInt - (xDiff/cellWidth).toInt()).toString()
                        }
                        -1*xDiff > cellWidth -> {
                            (tagInt + -1*(xDiff/cellWidth).toInt()).toString()
                        }
                        else -> tag
                    }
                } else if(swipeState == SwipeState.Vertical){
                    finalTag = when {
                        yDiff > cellWidth -> {
                            (tagInt - 10*(yDiff/cellWidth).toInt()).toString()
                        }
                        -1*yDiff > cellWidth -> {
                            (tagInt + -10*(yDiff/cellWidth).toInt()).toString()
                        }
                        else -> tag
                    }
                }
                checkIfRangeIsValid(v.tag.toString(), finalTag)
            }
        }
         true
    }
    private fun checkIfRangeIsValid(initTag: String, endTag: String){
        var found = false
        for(wordObj in wordArray){
            if(wordObj.checkLoc(initTag.toInt(), endTag.toInt(), swipeState == SwipeState.Horizontal)){
                // we got a match
                if(wordObj.found){
                    // the word has been already found
                    xInitial = -1f
                    yInitial = -1f
                    xDiff = -1f
                    yDiff = -1f
                    prevXDiff = -1f
                    prevYDiff = -1f
                    swipeState = SwipeState.Undefined
                    return
                }

                markCellsAsFound(initTag.toInt(), endTag.toInt(), swipeState == SwipeState.Horizontal)
                wordObj.found = true
                found = true
                break
            }
        }
        if (found){
            binding.greenCheck.visibility = View.VISIBLE
            Timer("delay", false).schedule(500) {
                requireActivity().runOnUiThread{
                    binding.greenCheck.visibility = View.GONE
                }
            }
            var showCongrats = true
            for(wordObj in wordArray){
                if(!wordObj.found){
                    showCongrats = false
                    break
                }
            }
            if (showCongrats){
                //congrats_layout.visibility = View.VISIBLE
                mediaplayer!!.stop()
                Sharedapp.gune.gune="2.Gunea 1"
                mostrarDialogoPersonalizado()
            }

        } else {
            //redX.visibility = View.VISIBLE
            Timer("delay", false).schedule(500) {
                requireActivity().runOnUiThread{
                    //redX.visibility = View.GONE
                }
            }
            unselectCellRange(initTag.toInt(), endTag.toInt(), swipeState == SwipeState.Horizontal)
        }

        // resetting values
        xInitial = -1f
        yInitial = -1f
        xDiff = -1f
        yDiff = -1f
        swipeState = SwipeState.Undefined
    }
    private fun unselectCellRange(initTag: Int, endTag: Int, isHorizontal: Boolean){
        var start = initTag
        var end = endTag
        if (endTag < initTag){
            start = endTag
            end = initTag
        }
        if(isHorizontal){
            for (i in start..end){
                unselectSingleCell(i.toString())
            }
        } else {
            for (i in start..end step 10){
                unselectSingleCell(i.toString())
            }
        }
    }
    private fun selectSingleCell(tag: String){
        val childCount = binding.wordsGrid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = binding.wordsGrid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                if(linearLayout.getChildAt(t).tag == tag){
                    linearLayout.getChildAt(t).background = ContextCompat.getDrawable(requireContext(), R.drawable.borde)
                    return
                }
            }
        }
    }
    private fun unselectSingleCell(tag: String){
        var tagInt = tag.toInt()
        val childCount = binding.wordsGrid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = binding.wordsGrid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                if(linearLayout.getChildAt(t).tag == tag){
                    if(!foundWordsFlags[tagInt/10][tagInt%10]){
                        linearLayout.getChildAt(t).background = ContextCompat.getDrawable(requireContext(), R.drawable.borde_unselected)
                    }
                    return
                }
            }
        }
    }
    private fun markCellsAsFound(initTag: Int, endTag: Int, isHorizontal: Boolean){
        var start = initTag
        var end = endTag
        if (endTag < initTag){
            start = endTag
            end = initTag
        }
        if(isHorizontal){
            for (i in start..end){
                foundWordsFlags[i/10][i%10] = true
            }
        } else {
            for (i in start..end step 10){
                foundWordsFlags[i/10][i%10] = true
            }
        }
    }
    private fun unslectAllCells(){
        val childCount = binding.wordsGrid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = binding.wordsGrid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                linearLayout.getChildAt(t).background = ContextCompat.getDrawable(requireContext(), R.drawable.borde_unselected)
            }
        }
    }
    private fun generateRandomLetters(){
        gridFlags = Array(gridSize) { BooleanArray(gridSize) { false } }
        foundWordsFlags = Array(gridSize) { BooleanArray(gridSize) { false } }
        val rnd = Random()
        var toggle: Boolean = rnd.nextInt(2) != 0

        for(r in 0 until gridSize){
            for (c in 0 until gridSize){
                gridLetters[r][c] = vocabulary[rnd.nextInt(vocabulary.length)].toString()
            }
        }

        // Positioning words to be found
        for (w in 0 until words.size){
            var found = false
            while (!found){
                var r = 0
                if(words[w].length < gridSize){
                    r = rnd.nextInt(gridSize - (words[w].length))
                } else if (words[w].length > gridSize){
                    // maybe throw exception ?
                    break
                }

                // checking all rows or columns depending on the toggle value
                var start = rnd.nextInt(gridSize - 1)
                for (n in 0 until gridSize){
                    var _n = (n + start) % gridSize
                    // checking if the row or column is empty enough to place the word
                    for (i in r until r + words[w].length ) {
                        if(toggle){
                            // looking along the row
                            if(gridFlags[_n][i] && gridLetters[_n][i] != words[w][i-r].toString()) {
                                break
                            } else if (i == r + words[w].length - 1) {
                                // we've reached the end
                                found = true
                            }
                        } else {
                            // looking at along the column
                            if(gridFlags[i][_n]&& gridLetters[i][_n] != words[w][i-r].toString()) {
                                break
                            } else if (i == r + words[w].length - 1) {
                                // we've reached the end
                                found = true
                            }
                        }
                    }
                    if(found) {
                        // Registering location in Word object
                        if(toggle){
                            wordArray[w].setLoc(_n*10 + r, toggle)
                        } else {
                            wordArray[w].setLoc(r*10 + _n, toggle)
                        }

                        for (i in r until r + words[w].length ) {
                            if(toggle){
                                // filling along the row
                                gridLetters[_n][i] = words[w][i-r].toString()
                                gridFlags[_n][i] = true
                            } else {
                                // filling at along the column
                                gridLetters[i][_n] = words[w][i-r].toString()
                                gridFlags[i][_n] = true
                            }
                        }
                        break
                    }
                }
                toggle = !toggle
            }
        }

        // displaying letters
        val childCount = binding.wordsGrid.childCount
        for (i in 0 until childCount){
            val linearLayout: LinearLayout = binding.wordsGrid.getChildAt(i) as LinearLayout
            for (t in 0 until linearLayout.childCount){
                (linearLayout.getChildAt(t) as TextView).text = gridLetters[i][t]
            }
        }
    }
    companion object {
        const val vocabulary = "ABCDEFGHIJKLMOPQRSTUVWSYZ"
        const val numWords = 6
        const val gridSize = 10

        var gridLetters = Array(gridSize) { Array<String>(gridSize) { "A" } }
        var gridFlags = Array(gridSize) { BooleanArray(gridSize) { false } }
        var foundWordsFlags = Array(gridSize) { BooleanArray(gridSize) { false } }

        val wordArray = Array<Letra>(numWords) { Letra("") }
        val words = arrayOf("ERMITA", "TONTORRA", "PAZKOA", "PENTEKOSTE","ERROMERIA","BAZKARIA")
    }
}