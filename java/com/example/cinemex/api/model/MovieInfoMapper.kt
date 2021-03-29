package com.example.cinemex.api.model

import com.example.cinemex.domain.model.MovieInfoDomain
import com.example.cinemex.domain.utils.EntityMapper

object MovieInfoMapper: EntityMapper<MovieInfo, MovieInfoDomain> {
    override fun mapFromEntity(entity: MovieInfo): MovieInfoDomain {
        return MovieInfoDomain(
            name = entity.name,
            label = entity.label,
            rating = entity.rating,
            cover = entity.cover,
            poster_big = entity.poster_big,
            info = InfoMovieMapper.mapFromEntity(entity.info)
        )
    }
}