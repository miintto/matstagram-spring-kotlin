package com.miintto.matstagram.place

import com.miintto.matstagram.api.place.domain.Place
import com.miintto.matstagram.api.place.domain.Tag
import com.miintto.matstagram.api.place.service.PlaceService
import com.miintto.matstagram.api.place.service.TagService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.anyLong
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class PlaceControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var placeService: PlaceService

    @MockBean
    private lateinit var tagService: TagService

    @Test
    @DisplayName("장소 상세정보 호출")
    @WithMockUser(username = "1", roles = ["USER"])
    fun testPlace() {
        given(placeService.searchPlace(anyLong(), anyLong())).willReturn(
            Place(userId = 1, placeName = "테스트장소", placeType = "restaurant")
        )

        val request = get("/place/1")
        mockMvc.perform(request).andExpect(status().isOk)
    }

    @Test
    @DisplayName("장소 리스트 호출")
    @WithMockUser(username = "1", roles = ["USER"])
    fun testPlaceList() {
        given(placeService.getPlaceListByUserId(anyLong())).willReturn(
            listOf(Place(userId = 1, placeName = "테스트장소", placeType = "restaurant"))
        )

        val request = get("/place")
        mockMvc.perform(request).andExpect(status().isOk)
    }

    @Test
    @DisplayName("태그 리스트 호출")
    @WithMockUser(username = "1", roles = ["USER"])
    fun testTagList() {
        given(tagService.getTagListByUserId(anyLong())).willReturn(
            listOf(Tag(userId = 1, tagName = "태그1"))
        )

        val request = get("/tag")
        mockMvc.perform(request).andExpect(status().isOk)
    }
}
