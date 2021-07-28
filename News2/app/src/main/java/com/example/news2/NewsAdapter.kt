package com.example.news2

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context:Context,val articles:List<Articles>):RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.items_news,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var current_item:Articles = articles[position]

        holder.newsTitle.text = current_item.title
        holder.newsDescription.text = current_item.description
        holder.newsAuthor.text = current_item.author
        Glide.with(context).load(current_item.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener{
            //Log.d("oooooooooooooooooo",current_item.url)

           // Toast.makeText(context,current_item.title ,Toast.LENGTH_SHORT).show()
            val intent = Intent(context,Detail_Activity::class.java)
            intent.putExtra("URL",current_item.url) //key-value pair
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return articles.size
    }
}

class  NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
    var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
    var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)
    var newsAuthor = itemView.findViewById<TextView>(R.id.newsAuthor)
}