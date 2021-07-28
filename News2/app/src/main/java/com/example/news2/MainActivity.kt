package com.example.news2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout


class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter :NewsAdapter
    private var articles = mutableListOf<Articles>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = NewsAdapter(this,articles)
        recyclerView.adapter = mAdapter
       // recyclerView.layoutManager = LinearLayoutManager(this)

        val linearLayoutManager = ZoomRecyclerLayout(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView) // Add your recycler view here
        recyclerView.isNestedScrollingEnabled = false


        getNews()


    }

    private fun getNews() {
        val news:Call<News> = NewsService.newsInstance.getHeadLines("in")
        news.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news:News? = response.body()
                if(news!=null)
                {
                    Log.d("THISSSSS",news.toString())
                    articles.addAll(news.articles)
                    mAdapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("ERRRRRROR","ERROR IN FETCHING",t)
            }
        } )
    }
}