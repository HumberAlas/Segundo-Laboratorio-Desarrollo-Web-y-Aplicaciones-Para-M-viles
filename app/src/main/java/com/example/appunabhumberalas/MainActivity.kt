package com.example.appunabhumberalas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    data class Persona(
        val nombre: String,
        val edad: String,
        val departamento: String
    )

    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etDepartamento: EditText
    private lateinit var btnAgregar: Button
    private lateinit var listaNombres: ListView
    private lateinit var tvDetalle: TextView

    private val personas = mutableListOf<Persona>()
    private val nombres = mutableListOf<String>()
    private lateinit var adaptador: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etDepartamento = findViewById(R.id.etDepartamento)
        btnAgregar = findViewById(R.id.btnAgregar)
        listaNombres = findViewById(R.id.listaNombres)
        tvDetalle = findViewById(R.id.tvDetalle)

        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres)
        listaNombres.adapter = adaptador

        btnAgregar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val edad = etEdad.text.toString().trim()
            val departamento = etDepartamento.text.toString().trim()

            if (nombre.isEmpty() || edad.isEmpty() || departamento.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val persona = Persona(nombre, edad, departamento)
                personas.add(persona)
                nombres.add(nombre)
                adaptador.notifyDataSetChanged()

                etNombre.text.clear()
                etEdad.text.clear()
                etDepartamento.text.clear()

                Toast.makeText(this, "Dato agregado correctamente", Toast.LENGTH_SHORT).show()
            }
        }

        listaNombres.setOnItemClickListener { _, _, position, _ ->
            val personaSeleccionada = personas[position]
            tvDetalle.text = "Edad: ${personaSeleccionada.edad}\nDepartamento: ${personaSeleccionada.departamento}"
        }
    }
}