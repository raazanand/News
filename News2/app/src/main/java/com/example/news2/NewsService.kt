package com.example.news2
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.create
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "5f60ae62fcbc4bdaa0d15164d7f1275b"

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country")country:String): Call<News>

}

object NewsService {
    val newsInstance :NewsInterface

    init {
        val retrofit: Retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        newsInstance = retrofit.create(NewsInterface::class.java)


    }
}