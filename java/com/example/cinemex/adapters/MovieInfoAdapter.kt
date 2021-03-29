package com.example.cinemex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemex.R

class MovieInfoAdapter(list: ArrayList<Pair<String,String>>): RecyclerView.Adapter<MovieInfoAdapter.MyViewHolder>() {
        private val list: ArrayList<Pair<String,String>> = list

        inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val infoType = itemView.findViewById<TextView>(R.id.infoType)
            val info = itemView.findViewById<TextView>(R.id.info)

            fun bindView(movieInfo: Pair<String, String>) {
                infoType.text = movieInfo.first
                info.text = movieInfo.second
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_info, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            if (holder != null) {
                holder.bindView(list[position])
            }
        }

        override fun getItemCount() = list.size
}