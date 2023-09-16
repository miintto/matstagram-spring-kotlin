package com.miintto.matstagram.api.place.domain.repository

import com.miintto.matstagram.api.place.domain.Place
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository : JpaRepository<Place, Long> {

    @EntityGraph(attributePaths = ["tags"])
    fun findByUserIdOrderById(userId: Long): List<Place>
}
