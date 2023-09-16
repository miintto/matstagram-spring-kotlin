package com.miintto.matstagram.api.place.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "t_place")
class Place(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var userId: Long,

    var placeName: String,

    var description: String? = null,

    var lat: Float? = null,

    var lng: Float? = null,

    var address: String? = null,

    var isActive: Boolean = true,

    var imageUrl: String? = null,

    var placeType: String,

    var createdDtm: LocalDateTime = LocalDateTime.now(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "t_place_tag",
        joinColumns = [JoinColumn(name = "place_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    var tags: List<Tag> = listOf()
)
