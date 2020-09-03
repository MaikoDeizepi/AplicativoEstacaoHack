package com.deizepi.app_estacao_hack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLoginEntrar.setOnClickListener {
            val email = edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = edtLoginSenha.text.toString().trim()

            if (email.isEmpty()) {
                edtLoginEmail.error = "Campo Obrigatório"
                edtLoginEmail.requestFocus()
            } else if (senha.isEmpty()) {
                edtLoginSenha.error = "Campo Obrigatório"
                edtLoginSenha.requestFocus()
            }else{
                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE

                )
                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")

                if (email == emailPrefs && senha == senhaPrefs) {
                    Toast.makeText(
                        this, "Usuario logado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    val mIntent = Intent(this, MainActivity::class.java)
                    mIntent.putExtra("INTENT_EMAIL",email)
                    startActivity(mIntent)
                    finishAffinity()
                }else{
                    Toast.makeText(this, "E-mail ou senha inválidos", Toast.LENGTH_SHORT).show()
                }
            }


        }
        btnLoginCadastrar.setOnClickListener {
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
        }
    }
}