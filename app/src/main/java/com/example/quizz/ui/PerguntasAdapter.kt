package com.example.quizz.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quizz.R
import com.example.quizz.entidades.Jogador
import com.example.quizz.entidades.Pergunta
import com.example.quizz.services.LoginService
import kotlinx.android.synthetic.main.layout_resposta.view.btResposta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PerguntasAdapter(private val pergunta: Pergunta) :
    RecyclerView.Adapter<PerguntasAdapter.ResultViewHolder>() {
    init {
        configureRetrofit()
    }
    private var respostas: MutableList<String> = pergunta.incorrect_answers.toMutableList()

    init {
        respostas.add(pergunta.correct_answer)
        respostas.shuffle()

    }

    lateinit var service: LoginService

    override fun getItemViewType(position: Int) = R.layout.layout_pergunta

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerguntasAdapter.ResultViewHolder =
        ResultViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun getItemCount() = respostas.size


    override fun onBindViewHolder(holder: PerguntasAdapter.ResultViewHolder, position: Int) =
        holder.preencherView(respostas[position])

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(perguntas: String) {
            itemView.btResposta.text = perguntas

            itemView.btResposta.setOnClickListener {
                if(itemView.btResposta.text == pergunta.correct_answer){

                    enviarDados()

                }else{
                    enviarDados()
                    configureRetrofit()
                }
            }
        }

    }

    fun enviarDados(){
        service.pontuacao("qq@qq.com", "qqqqqqqq", 10).enqueue(object : Callback<Jogador> {
            override fun onFailure(call: Call<Jogador>, t: Throwable) {
            }

            override fun onResponse(call: Call<Jogador>, response: Response<Jogador>) {
                val pontuacao = response.body()!!
                if (pontuacao.sucesso) {

                } else {

                }
            }


        })

    }

    private fun configureRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            service = retrofit.create<LoginService>(LoginService::class.java)
    }

}
