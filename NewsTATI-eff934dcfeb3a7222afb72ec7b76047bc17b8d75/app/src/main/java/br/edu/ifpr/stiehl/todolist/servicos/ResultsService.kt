package br.edu.ifpr.stiehl.todolist.servicos

import br.edu.ifpr.stiehl.todolist.entidades.NewsResult
import retrofit2.Call
import retrofit2.http.*

interface ResultsService {
    @Headers("Accept: application/json")
    @GET("top-headlines")
    fun news(
        @Query("country")
        country:String,
        @Query("apiKey")
        apiKey:String,
        @Query("q")
        q:String
    ):Call<NewsResult>
}