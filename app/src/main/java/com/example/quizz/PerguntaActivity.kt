package com.example.quizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizz.entidades.Pergunta
import com.example.quizz.entidades.PerguntaResponse
import com.example.quizz.services.QuestaoService
import com.example.quizz.ui.PerguntasAdapter
import kotlinx.android.synthetic.main.activity_pergunta.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PerguntaActivity : AppCompatActivity() {
    lateinit var service: QuestaoService
    lateinit var retrofit: Retrofit
    lateinit var adapter: PerguntasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pergunta)
        setRetrofit()
        carregaDados()
    }

    fun setRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(QuestaoService::class.java)
    }

    fun carregaDados() {
        service.perguntas("10", "", "").enqueue(object : Callback<PerguntaResponse> {
            override fun onFailure(call: Call<PerguntaResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<PerguntaResponse>,
                response: Response<PerguntaResponse>
            ) {
                val resultado = response.body()
                if (resultado != null) {
                    var questao = resultado!!.results[0]!!.question!!
                    txtQuestao.text = questao
                    configuraRecyclerView(resultado.results[0])
                }
            }
        })

    }

    fun configuraRecyclerView(pergunta: Pergunta) {
        adapter = PerguntasAdapter(pergunta)
        perguntas.adapter = adapter
        perguntas.layoutManager = LinearLayoutManager(this)
    }
}

