package com.miintto.matstagram.api.place.service

import com.miintto.matstagram.api.place.domain.Tag
import com.miintto.matstagram.api.place.domain.repository.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TagService {

    @Autowired
    private lateinit var tagRepository: TagRepository

    fun getTagListByUserId(userId: Long): List<Tag> {
        return tagRepository.findByUserIdOrderById(userId)
    }
}
