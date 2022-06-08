package com.example.assignment_androidnc.news

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_androidnc.databinding.ItemNewsBinding
import com.squareup.picasso.Picasso

class AdapterNews(var context: Context, var list:ArrayList<NewsModel>) : RecyclerView.Adapter<AdapterNews.ViewHolder>() {
    class ViewHolder(binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.itemNewsImg
        val title = binding.itemNewsTitle
        val des = binding.itemNewsDes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsModel = list[position]
        holder.des.text =  newsModel.des
        holder.title.text = newsModel.title
        Picasso.get().load(newsModel.url).into(holder.img)

        holder.title.setOnClickListener {
            val intent = Intent(context,WebViewActivity::class.java)
            intent.putExtra("linkNews",newsModel.link)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size
}