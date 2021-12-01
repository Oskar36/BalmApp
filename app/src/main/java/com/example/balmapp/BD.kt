package com.example.balmapp


import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore


class BD {

    companion object {
        fun insertarApodo(nombre: String) {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            val apodo = hashMapOf(
                "puntuacion" to "0",
            )
            db.collection("apodos").document(nombre)
                .set(apodo)
        }
        fun insertarNuevaPartida(nombre: String) {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            val partida = hashMapOf(
                "gune" to "0",
                "nombre" to nombre,
                "puntuacion_partida" to "0"
            )
            db.collection("partidas").document()
                .set(partida)
                .addOnSuccessListener {
                }
                .addOnFailureListener { }
        }
        fun actualizarPartida(nombre: String,puntuacion: String,gune: String,eliminar:Boolean){
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            val partida = hashMapOf(
                "gune" to gune,
                "nombre" to nombre,
                "puntuacion_partida" to puntuacion
            )
            db.collection("partidas").whereEqualTo("nombre","$nombre")
                .get()
                .addOnSuccessListener {
                    if(eliminar){
                        insertarNuevaPartida(nombre)
                    }
                }
                .addOnFailureListener { }
        }
        fun guardarDatoPartida(
            gune: String,
            nombre: String,
            puntuacion_partida: String
        ) {
            //el id se genera automaticamente
            //comprobar el nombre
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            val dato = hashMapOf(
                "gune" to gune,
                "nombre" to nombre,
                "puntuacion_partida" to puntuacion_partida
            )
            db.collection("partidas").document().set(dato)
                .addOnSuccessListener {
                    System.out.println("operacion correcta")
                }
                .addOnFailureListener {
                    System.out.println("ha fallado")
                }


        }

        fun insertarPuntuacionPartida(nombre: String, puntuacion: String) {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            val cambios = hashMapOf<String, Any>(
                "puntuacion" to puntuacion
            )
            db.collection("partidas")
                .whereEqualTo("nombre", nombre)
                .get()
                .addOnSuccessListener {
                    it.forEach {
                        it.reference.update(cambios)
                        System.out.println("operacion correcta")
                    }
                }
                .addOnFailureListener {
                    System.out.println("ha fallado")
                }

        }

        fun insertarGunePartida(db: FirebaseFirestore, nombre: String, gune: String) {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            val cambios = hashMapOf<String, Any>(
                "gune" to gune
            )
            db.collection("partidas")
                .whereEqualTo("nombre", nombre)
                .get()
                .addOnSuccessListener {
                    it.forEach {
                        it.reference.update(cambios)
                        System.out.println("operacion correcta")
                    }
                }
                .addOnFailureListener {
                    System.out.println("ha fallado")
                }

        }
    }

}


