package com.example.quizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizz.entidades.Categoria
import com.example.quizz.entidades.CategoriaMaior
import com.example.quizz.services.CategoriaService
import com.example.quizz.ui.CategoriaAdapter
import kotlinx.android.synthetic.main.activity_config.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigActivity : AppCompatActivity() {
    lateinit var service: CategoriaService
    lateinit var retrofit: Retrofit
    lateinit var adapter: CategoriaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
    }

    fun setRetrofit() {
         retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(CategoriaService::class.java)
    }

    fun carregaDados() {
        service.categories().enqueue(object : Callback<CategoriaMaior> {
            override fun onFailure(call: Call<CategoriaMaior>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<CategoriaMaior>,
                response: Response<CategoriaMaior>
            ) {
                val results = response.body()
                if (results != null)
                    configuraRecyclerView(results?.categoriaMaior)
            }
        })
    }

    fun configuraRecyclerView(results: List<Categoria>) {
        adapter = CategoriaAdapter(results)
        categorias.adapter = adapter
        categorias.layoutManager = LinearLayoutManager(this)
    }
}
