package com.example.cinemex.api.model

data class MovieInfo(
        val type: ArrayList<String>,
        val featured_text: String,
        val spotlight: Boolean,
        val url: String,
        val data_created: String,
        val featured: Boolean,
        val display_title: Boolean,
        val cover: String,
        val parent_id: Int,
        val rating: String,
        val label: String,
        val score: Double,
        val poster_medium: String,
        val ribbons: ArrayList<Ribbons>,
        val info: InfoMovie,
        val poster_big: String,
        val poster_mall: String,
        val id: Int,
        val name: String,
        val poster_url: String,
        val attributes: ArrayList<String>
)