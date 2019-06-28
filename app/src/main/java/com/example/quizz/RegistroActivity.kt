package com.example.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizz.entidades.Registro
import com.example.quizz.services.RegistroService
import kotlinx.android.synthetic.main.activity_registro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroActivity : AppCompatActivity() {

    lateinit var service: RegistroService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        configureRetrofit()

        btCadastrar.setOnClickListener {
            validarSenha()
        }

    }

    private fun configureRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<RegistroService>(RegistroService::class.java)
    }

    private fun validarSenha(){
        if(txtSenha.text.toString().equals(txtConfirmSenha.text.toString())){
            val a: Call<Registro> = service.registrar(txtNome.text.toString(), txtEmail.text.toString(),txtSenha.text.toString())
            val intent = Intent(this@RegistroActivity, MainActivity::class.java)
              startActivity(intent)
            a.enqueue(object : Callback<Registro> {
                override fun onFailure(call: Call<Registro>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Registro>, response: Response<Registro>) {
                    Toast.makeText(this@RegistroActivity, "Registrado com sucesso", Toast.LENGTH_SHORT).show()

                }
            })

        } else{
            Toast.makeText(this@RegistroActivity, "As senhas não coincidem! Tente novamente.", Toast.LENGTH_SHORT).show()

        }

    }
}
