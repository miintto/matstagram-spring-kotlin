package com.miintto.matstagram.controller

import com.miintto.matstagram.domain.Tag
import com.miintto.matstagram.service.TagService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class TagControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var tagService: TagService

    @Test
    @DisplayName("태그 리스트 호출")
    @WithMockUser(username = "1", roles = ["USER"])
    fun testTagList() {
        BDDMockito.given(tagService.getTagListByUserId(BDDMockito.anyLong())).willReturn(
            listOf(Tag(userId = 1, tagName = "태그1"))
        )

        val request = MockMvcRequestBuilders.get("/tag")
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
    }
}
