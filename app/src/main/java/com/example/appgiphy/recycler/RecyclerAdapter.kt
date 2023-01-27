package com.example.appgiphy.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appgiphy.R
import com.example.appgiphy.model.Datum
import com.example.appgiphy.model.Gifs

class RecyclerAdapter(var context: Fragment) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var movieList: MutableList<Datum> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvMovieName.text = movieList.get(position).title
        Glide.with(context).asGif().load(movieList.get(position).images.original.url).into(holder.imageView)

    }

    fun setMovieListItems(movieList: Gifs) {
        this.movieList.addAll(movieList.datumList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val tvMovieName: TextView = itemView!!.findViewById(R.id.textView)
        val imageView: ImageView = itemView!!.findViewById(R.id.imageView)

    }

}