package com.example.quizz.services

import com.example.quizz.entidades.Login
import com.example.quizz.entidades.Registro
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegistroService {
    @Headers("Accept: application/json")
    @FormUrlEncoded

    @POST("usuario/registrar")
    fun registrar(
        @Field("nome")
        nome:String,
        @Field("email")
        email:String,
        @Field("senha")
        senha: String
    ): Call<Registro>
}