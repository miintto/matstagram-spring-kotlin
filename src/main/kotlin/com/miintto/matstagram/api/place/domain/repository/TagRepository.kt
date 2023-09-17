package com.miintto.matstagram.api.place.domain.repository

import com.miintto.matstagram.api.place.domain.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : JpaRepository<Tag, Long> {

    fun findByUserIdOrderById(userId: Long): List<Tag>
}
