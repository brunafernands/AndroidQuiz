package com.example.quizz.services

import com.example.quizz.entidades.CategoriaMaior
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoriaService {
    @Headers("Accept: application/json")
    @GET("api_category.php")
    fun categories(
    ): Call<CategoriaMaior>
}