package com.example.quizz.services
import com.example.quizz.entidades.Jogador
import com.example.quizz.entidades.Login
import com.example.quizz.entidades.RankingResponse
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @Headers("Accept: application/json")
    @FormUrlEncoded

    @POST("usuario/login")
    fun logar(
        @Field("email")
        email:String,
        @Field("senha")
        password: String
    ): Call<Login>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PUT("usuario/pontuacao")
    fun pontuacao(
        @Field("email")
        email:String,
        @Field("senha")
        senha:String,
        @Field("pontos")
        pontos:Int
    ): Call<Jogador>

    @Headers("Accept: application/json")
    @GET("ranking")
    fun ranking(
    ): Call<RankingResponse>
}