package com.example.cinemex.api.response

import com.example.cinemex.api.model.MovieMapper
import com.example.cinemex.domain.model.MovieResponseDomain
import com.example.cinemex.domain.utils.EntityMapper

object MovieResponseMapper: EntityMapper<MovieResponse,MovieResponseDomain> {
    override fun mapFromEntity(entity: MovieResponse): MovieResponseDomain {
        return MovieResponseDomain(
                movieList = MovieMapper.fromEntityList(entity.data)
        )
    }

}