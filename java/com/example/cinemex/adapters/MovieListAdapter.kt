package com.example.cinemex.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemex.R
import com.example.cinemex.domain.model.MovieDomain
import com.facebook.drawee.view.SimpleDraweeView

class MovieListAdapter(private val listener: MovieListener): RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {
    private lateinit var list: List<MovieDomain>

    inner class MyViewHolder    (itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.movieTitle)
        val movieLanguage = itemView.findViewById<TextView>(R.id.movieLanguage)
        val movieRating = itemView.findViewById<TextView>(R.id.movieRating)
        val cinemaName = itemView.findViewById<TextView>(R.id.cinemaName)
        val movieHour = itemView.findViewById<TextView>(R.id.movieHour)
        val startIn = itemView.findViewById<TextView>(R.id.startIn)
        val poster = itemView.findViewById<SimpleDraweeView>(R.id.poster)
        val info = itemView.findViewById<RecyclerView>(R.id.info)
        val arrow = itemView.findViewById<ImageButton>(R.id.arrowDown)
        val play = itemView.findViewById<ImageView>(R.id.play)

        fun bindView(serviceMovie: MovieDomain){
            title?.text = serviceMovie.movie.name
            movieLanguage?.text = serviceMovie.movie.label
            movieRating?.text = serviceMovie.movie.rating
            cinemaName?.text = serviceMovie.cinema.name
            movieHour?.text = serviceMovie.movieStart()
            startIn?.text = if (serviceMovie.oldMovie()) {
                                itemView.context.getString(R.string.starting)
                            }else{
                                if(serviceMovie.hoursLeft() == 12){
                                    itemView.context.getString(R.string.start_in,"${serviceMovie.minutesLeft()}m.")
                                }else{
                                    itemView.context.getString(R.string.start_in,"${serviceMovie.hoursLeft()}hr. ${serviceMovie.minutesLeft()}m.")
                                }
                            }

            poster?.setImageURI(serviceMovie.movie.getCoverUrl())
            arrow.setOnClickListener {
                serviceMovie.isShowingInfo = !serviceMovie.isShowingInfo
                listener.setList(list)
            }
            play?.setOnClickListener {
                listener.onShowTrailer(serviceMovie.movie.info.getMovieId(),serviceMovie.movie.getCoverBigUrl())
            }

            val viewAdapter = MovieInfoAdapter(
                arrayListOf(
                    Pair(
                        itemView.context.getString(R.string.synopsis),
                        serviceMovie.movie.info.sinopsis
                    ),
                    Pair(
                        itemView.context.getString(R.string.original_title),
                        serviceMovie.movie.info.original_title
                    ),
                    Pair(
                        itemView.context.getString(R.string.classification),
                        serviceMovie.movie.info.rating
                    ),
                    Pair(itemView.context.getString(R.string.cast), serviceMovie.movie.info.cast),
                    Pair(
                        itemView.context.getString(R.string.director),
                        serviceMovie.movie.info.director
                    ),
                    Pair(
                        itemView.context.getString(R.string.country),
                        serviceMovie.movie.info.country
                    ),
                    Pair(itemView.context.getString(R.string.year), serviceMovie.movie.info.year),
                    Pair(
                        itemView.context.getString(R.string.genre),
                        serviceMovie.movie.info.genre[0]
                    )
                )
            )
            info.adapter = viewAdapter
        }

        fun changeInfoVisibility(mustShow: Boolean) {
            if (mustShow) {
                info.visibility = View.VISIBLE
                arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            } else {
                info.visibility = View.GONE
                arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(holder!=null){
            holder.changeInfoVisibility(list[position].isShowingInfo)
            holder.bindView(list[position])
        }
    }

    fun refreshData(movieList: List<MovieDomain>) {
        list = movieList
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    interface MovieListener {
        fun setList(mList: List<MovieDomain>)
        fun onShowTrailer(movieId: String,posterUrl: String)
    }
}