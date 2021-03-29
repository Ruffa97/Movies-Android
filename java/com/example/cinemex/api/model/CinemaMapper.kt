package com.example.cinemex.api.model

import com.example.cinemex.domain.model.CinemaDomain
import com.example.cinemex.domain.utils.EntityMapper

object CinemaMapper: EntityMapper<Cinema, CinemaDomain> {
    override fun mapFromEntity(entity: Cinema): CinemaDomain {
        return CinemaDomain(
                name = entity.name,
                id = entity.id
        )
    }
}