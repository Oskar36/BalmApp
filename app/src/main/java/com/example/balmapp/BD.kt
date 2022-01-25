package com.example.balmapp


import com.google.firebase.firestore.FirebaseFirestore


class BD {

    companion object {
        //inserta un apodo en la bd y pone el gune en 1 para que el usuario pueda empezar a jugar
        fun insertarApodo(nombre: String) {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            val apodo = hashMapOf(
                "gune" to "1",
            )
            db.collection("apodos").document(nombre)
                .set(apodo)
        }
        //pone el gune 1 para reiniciar la partida
        fun insertarNuevaPartida(nombre: String) {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            db.collection("apodos").document(nombre)
                .update("gune","1")
        }
        //carga en gune que va el usuario pasado
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

        //actualiza el gune por el gune que se le pasa a el usuario que se le pasa
        fun actualizar_gune(gune:Int,nombre: String){
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            db.collection("apodos").document(nombre)
                .update("gune",gune)
        }
    }
}


