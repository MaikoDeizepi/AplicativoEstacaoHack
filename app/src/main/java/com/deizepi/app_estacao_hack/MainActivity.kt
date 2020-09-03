package com.deizepi.app_estacao_hack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = intent.getStringExtra("INTENT_EMAIL")
        val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)
        val nomePrefs = sharedPrefs.getString("NOME", "")
        val sobreNomePrefs = sharedPrefs.getString("SOBRENOME", "")
        val generoPrefs = sharedPrefs.getString("GENERO", "")

        txvMainNome.text = "$nomePrefs $sobreNomePrefs"
        txvMainEmail.text = email
        txvMainGenero.text = generoPrefs

        if (generoPrefs == "Masculino") {
            txvMainSaudacao.text = "Seja Bem vindo"
        } else if (generoPrefs == "Feminino") {
            txvMainSaudacao.text = "Seja Bem vinda"
        } else {
            txvMainSaudacao.text = "Seja Bem vindx"
        }

        btnMainSair.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Atenção!!")
                .setMessage("Você realmente deseja sair?")
                .setPositiveButton("SIM") { _, _ ->
                    val mIntent = Intent(this, LoginActivity::class.java)
                    startActivity(mIntent)
                    finishAffinity()
                }
                .setNeutralButton("CANCELAR") { _, _ -> }
                .setCancelable(false)
                .create()
                .show()
        }

        btnMainSite.setOnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
            finishAffinity()
        }
    }


}