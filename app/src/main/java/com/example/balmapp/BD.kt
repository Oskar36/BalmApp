package com.example.balmapp


import com.google.firebase.firestore.FirebaseFirestore


class BD {

    companion object {
        //si llama al la funcion para insertar un apodo y no se puede porque el nombre
        // ya ha habido un apodo con ese nombre se devuelve false y si la insercion es correcta se devuelve true
        fun guardarDatoApodo(nombre: String, puntuacion: String): Boolean {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            var insertado: Boolean = false
            //comprobar el nombre
            if (combrobarnombre(nombre) == false) {
                val dato = hashMapOf(
                    "id" to nombre,
                    "puntuacion" to puntuacion
                )
                db.collection("apodos").document().set(dato)
                    .addOnSuccessListener {
                        System.out.println("operacion correcta")
                        insertado = true
                    }
                    .addOnFailureListener {
                        System.out.println("ha fallado")

                    }
            }
            return insertado
        }


        private fun combrobarnombre( nombre: String): Boolean {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            var control: Boolean = false
            db.collection("apodos").get()
                .addOnSuccessListener { documentos ->

                    //leemos cada fila de apodo
                    for (documento in documentos) {
                        if ("${documento.id}" == nombre && control == false) {
                            control == true
                        }
                    }

                }
                .addOnFailureListener {
                }

            //en caso de que el apodo que se intente insertar ya este guardado
            return control
        }

        fun insertarPuntuacionApodo( nombre: String, puntuacion: String) {
            val db: FirebaseFirestore= FirebaseFirestore.getInstance()
            val cambios = hashMapOf<String, Any>(
                "puntuacion" to puntuacion
            )
            db.collection("apodos")
                .whereEqualTo("id", nombre)
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


