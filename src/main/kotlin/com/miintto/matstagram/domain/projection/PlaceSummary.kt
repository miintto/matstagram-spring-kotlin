package com.miintto.matstagram.domain.projection

interface PlaceSummary {

    val id: Long

    val placeName: String

    val description: String

    val lat: Float?

    val lng: Float?

    val imageUrl: String?
}
