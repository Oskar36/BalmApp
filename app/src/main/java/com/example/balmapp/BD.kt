package com.example.balmapp


import android.os.SystemClock
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore


class BD {

    companion object {
        fun insertarApodo(nombre: String) {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            val apodo = hashMapOf(
                "gune" to "1",
            )
            db.collection("apodos").document(nombre)
                .set(apodo)
        }
        fun insertarNuevaPartida(nombre: String) {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            db.collection("apodos").document(nombre)
                .update("gune","1")
        }
        fun cargarPartida(nombre: String){
            var gune=0
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            db.collection("apodos").document(nombre)
                .get()
                .addOnSuccessListener { document1 ->
                   gune= document1.get("gune").toString().toInt()
                    gune--
                    NavFrag.gune=gune
                }
                .addOnFailureListener { }
        }
    }
}


