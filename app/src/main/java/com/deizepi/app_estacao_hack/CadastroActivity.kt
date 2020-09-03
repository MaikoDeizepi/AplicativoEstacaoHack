package com.deizepi.app_estacao_hack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val listaGenero = arrayListOf("Selecione o Gênero", "Feminino", "Masculino", "Outros")
        val generoAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            listaGenero
        )
        spnCadastroGenero.adapter = generoAdapter



        btnCadastrar.setOnClickListener {
            val nome = edtCadastrarNome.text.toString().trim()
            val sobrenome = edtCadastrarSobrenome.text.toString().trim()
            val email = edtCadastrarEmail.text.toString().trim().toLowerCase()
            val senha = edtCadastrarSenha.text.toString().trim()
            val genero = spnCadastroGenero.selectedItem.toString()

            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || genero == listaGenero[0]) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            } else {
                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE
                )
                val editPrefs = sharedPrefs.edit()
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("GENERO", genero)
                editPrefs.apply()

                val mIntent = Intent(this, MainActivity::class.java)
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)
                finishAffinity()

            }


        }
    }
}
