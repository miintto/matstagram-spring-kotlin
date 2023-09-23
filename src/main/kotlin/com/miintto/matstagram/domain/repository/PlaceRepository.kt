package com.miintto.matstagram.domain.repository

import com.miintto.matstagram.domain.Place
import com.miintto.matstagram.domain.projection.PlaceSummary
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository : JpaRepository<Place, Long> {

    @EntityGraph(attributePaths = ["tags"])
    fun findByUserIdOrderById(userId: Long): List<Place>

    fun findByIdIn(ids: List<Long>): List<PlaceSummary>
}
