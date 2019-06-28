package com.example.quizz.services

import com.example.quizz.entidades.PerguntaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface QuestaoService {
    @Headers("Accept: application/json")

    @GET("api.php")

    fun perguntas(
        @Query("amount")
        amount: String,
        @Query("category")
        category: String,
        @Query("difficulty")
        difficulty: String
    ): Call<PerguntaResponse>
}