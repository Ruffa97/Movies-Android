package com.example.cinemex.api.model

import com.example.cinemex.domain.model.InfoMovieDomain
import com.example.cinemex.domain.utils.EntityMapper

object InfoMovieMapper: EntityMapper<InfoMovie, InfoMovieDomain> {
    override fun mapFromEntity(entity: InfoMovie): InfoMovieDomain {
        return InfoMovieDomain(
                sinopsis = entity.sinopsis,
                original_title = entity.original_title,
                rating = entity.rating,
                country = entity.country,
                cast = entity.cast,
                director = entity.director,
                trailer = entity.trailer,
                year = entity.year,
                genre = entity.genre
        )
    }
}