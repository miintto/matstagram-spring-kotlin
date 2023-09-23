package com.miintto.matstagram.service

import com.miintto.matstagram.domain.Tag
import com.miintto.matstagram.domain.repository.TagRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class TagServiceTests {

    @MockBean
    private lateinit var tagRepository: TagRepository

    @Autowired
    private lateinit var tagService: TagService

    @Test
    @DisplayName("태그 리스트 조회")
    fun testSearchTagList() {
        val userId = 100L
        val tag = Tag(userId = userId, tagName = "태그1")
        given(tagRepository.findByUserIdOrderById(userId)).willReturn(listOf(tag))

        tagService.getTagListByUserId(userId)

        verify(tagRepository).findByUserIdOrderById(userId)
    }
}
