package com.example.cinemex.api.model

import com.example.cinemex.domain.model.MovieDomain
import com.example.cinemex.domain.utils.EntityMapper
import javax.inject.Inject

object MovieMapper: EntityMapper<Movie, MovieDomain> {

    override fun mapFromEntity(entity: Movie): MovieDomain {
        return MovieDomain(
                isShowingInfo = false,
                datetime = entity.datetime,
                timestamp = entity.timestamp,
                cinema = CinemaMapper.mapFromEntity(entity.cinema),
                id = entity.id,
                tz_offset = entity.tz_offset,
                movie = MovieInfoMapper.mapFromEntity(entity.movie)

        )
    }

    fun fromEntityList(list: List<Movie>): List<MovieDomain>{
        return list.map { mapFromEntity(it) }
    }
}