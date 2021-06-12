package com.example.appclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var tvCiudad:TextView? = null
    var tvGrados:TextView? = null
    var tvEstatus:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstatus = findViewById(R.id.tvEstatus)

        val ciudad = intent.getStringExtra("com.example.appclima.ciudades.CIUDAD")

        if(Network.hayRed(this)) {
            // Ejecutar solicitud HTTP
            val url:String = "https://api.openweathermap.org/data/2.5/weather?id=${ciudad}&appid=148fb146ceb34e694bf33b8ea893b78a&units=metric&lang=es"
            solicitudHTTPVolley(url)

        } else {
            // Mostrar Mensaje de Error
            Toast.makeText(this, "No se encuentra la información", Toast.LENGTH_SHORT).show()

        }

    }

    private fun solicitudHTTPVolley(url:String) {
        val queue = Volley.newRequestQueue(this)

        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String>{
            response ->
            try {
                Log.d("solicitudHTTPVolley", response)

                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)
                tvCiudad?.text = ciudad.name
                tvGrados?.text = ciudad.main?.temp.toString() + "°"
                tvEstatus?.text = ciudad.weather?.get(0)?.description

            } catch (e: Exception) {}
        }, Response.ErrorListener {  })

        queue.add(solicitud)
    }
}