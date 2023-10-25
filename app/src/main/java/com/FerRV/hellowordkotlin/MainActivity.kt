


package com.FerRV.hellowordkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    // TextToSpeech.OnInitListener: Interfaz que permite implementar el método onInit

    // --------------------------------
    // Variables globales
    private var tts: TextToSpeech? = null // Variable para el TextToSpeech
    // TextToSpeech: Clase que permite convertir texto a voz
        // ? = null: Permite que la variable pueda ser nula

    // --------------------------------
    override fun onCreate(savedInstanceState: Bundle?) { // Función que se ejecuta al crear la actividad
        // savedInstanceState: Objeto que contiene el estado de la actividad
        // Bundle: Objeto que contiene el estado de la actividad
        super.onCreate(savedInstanceState) // Llamada al constructor de la clase padre
        setContentView(R.layout.activity_main) // Establece el layout de la actividad

        // ----------------------------------
        // Quitamos el valor null a la variable tts
        tts = TextToSpeech(this, this)
        // TextToSpeech(this, this): Constructor de la clase TextToSpeech
            // this: Contexto de la aplicación
            // this: Implementación de la interfaz OnInitListener


        // ----------------------------------
        // Vincular el layout con el código
        // con una variable...
        //var titulo: String = findViewById<TextView>(R.id.titulo).text.toString()
        // String: Tipo de dato de la variable
        // toString(): Método que convierte el valor de la variable a String
        // findViewById<TextView>(R.id.titulo): Busca el elemento
            // con id "titulo" y lo convierte a TextView

        // ----------------------------------
        // Recojemmos el texto del EditText
        //var messageS: String = findViewById<TextView>(R.id.etMessage).text.toString()

        // ----------------------------------
        // Primer intento de que hable
        // Habilitamos el botón
        // busca y asigna a la variable messageS el texto del EditText
        // y lo enviamos a la función speak()
        findViewById<Button>(R.id.btnSpeak).setOnClickListener {
            val messageS: String = findViewById<TextView>(R.id.etMessage).text.toString()
            speak(messageS)
        }
    }

    // --------------------------------
    // Función que se ejecuta al presionar el botón y hablar
    private fun speak (message: String){
        // message: Texto a convertir a voz en caso de que el EditText este vacio
        val errM: String = "You need to enter a message"
        // Verificamos que message no este vacio
        if (message.isEmpty()){
            // isEmpty(): Método que verifica si la variable esta vacia
            // Que repita el mensaje de error
            tts!!.speak(errM, TextToSpeech.QUEUE_FLUSH, null, "")

        } else {
            // Que hable el mensaje
            tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
        }
        // tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, ""): Método que convierte texto a voz
            // message: Texto a convertir a voz
            // TextToSpeech.QUEUE_FLUSH: Constante que indica que se debe eliminar el mensaje anterior
            // null: Parámetro que indica que no se requiere un parámetro adicional
            // "": Parámetro que indica que no se requiere un parámetro adicional
    }


    // --------------------------------
    // Cuando "Class MainActivity" esta en rojo, es porque no se ha implementado
    // todos los metodos o en su defecto, no se han terminado de implementar
    // todos los metodos de la interfaz (Implementar metodos con Ctrl + I)
    override fun onInit(status: Int) {
        // status: Estado de la inicialización
        // Int: Tipo de dato de la variable
        // onInit: Método que se ejecuta cuando se inicializa el TextToSpeech


        // ----------------------------------
        // Verificamos si se puede "hablar"
        if (status == TextToSpeech.SUCCESS) {
            // TextToSpeech.SUCCESS: Constante que indica que la inicialización fue exitosa
            // Habilitamos el botón
            findViewById<TextView>(R.id.btnSpeak).isEnabled = true
            // isEnabled: Método que habilita el botón
            // Si se puede hablar, habilitamos el botón
            // en su defecto, no se habilita
            // Mensaje de éxito con Toast por 3 segundos
            Toast.makeText(this, "Se puede hablar", Toast.LENGTH_LONG).show()
            // Selección de idioma
            tts!!.language = Locale.UK // Establecemos el idioma a inglés (UK)
        } else {
            // Deshabilitamos el botón
            findViewById<TextView>(R.id.btnSpeak).isEnabled = false
            // Mensajde de error con Toast por 3 segundos
            Toast.makeText(this, "No se puede hablar", Toast.LENGTH_LONG).show()
            // Toast.makeText(this, "No se puede hablar", Toast.LENGTH_LONG).show():
            // Método que muestra un mensaje
                // this: Contexto de la aplicación
                // "No se puede hablar": Mensaje a mostrar
                // Toast.LENGTH_LONG: Duración del mensaje
                // show(): Método que muestra el mensaje
        }
    }
}