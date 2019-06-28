package com.example.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizz.entidades.Login
import com.example.quizz.services.LoginService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences



class MainActivity : AppCompatActivity() {

    lateinit var service: LoginService
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isUsuarioLogado())
            iniciarJogo()
        else {
            configureRetrofit()

            btLogar.setOnClickListener {
                carregaDados()
                val intent = Intent(this@MainActivity, ConfigActivity::class.java)
                startActivity(intent)
            }

            btCadastrar.setOnClickListener {
                val intent = Intent(this@MainActivity, RegistroActivity::class.java)
                startActivity(intent)

            }
        }

        prefs = getSharedPreferences("activityprefs", Context.MODE_PRIVATE)
    }

    private fun configureRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<LoginService>(LoginService::class.java)
    }

    private fun carregaDados() {
        service.logar(txtEmail.text.toString(), txtSenha.text.toString()).enqueue(object : Callback<Login> {
            override fun onFailure(call: Call<Login>, t: Throwable) {
            }

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                val login = response.body()!!
                if (login.sucesso) {
                    Toast.makeText(this@MainActivity, login.mensagem, Toast.LENGTH_SHORT).show()
                    salvaPreferencia()
                    iniciarJogo()
                } else {
                    btLogar.text = login.mensagem
                    Toast.makeText(this@MainActivity, login.mensagem, Toast.LENGTH_SHORT).show()
                }
            }


        })
    }

    private fun iniciarJogo() {
        Toast.makeText(this@MainActivity, "Entrando no Jogo", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, PerguntaActivity::class.java)
        startActivity(intent)
    }



    private fun salvaPreferencia(){
        val editor = prefs.edit()
        editor.putString("email", txtEmail.text.toString())
        editor.putString("senha", txtSenha.text.toString())
        editor.commit()



    }

    private fun isUsuarioLogado() = prefs.contains("email")
//       //exemplo val string = prefs.getString("email","")
//        prefs.contains("email")
//        prefs.getString("email", txtEmail.text.toString())
//        prefs.getString("senha", txtSenha.text.toString())
//    }
}
