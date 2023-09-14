package com.miintto.matstagram.auth

import com.google.gson.Gson
import com.miintto.matstagram.api.auth.dto.LoginInfo
import com.miintto.matstagram.api.auth.dto.RegisterInfo
import com.miintto.matstagram.api.auth.service.LoginService
import com.miintto.matstagram.api.auth.service.RegisterService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var registerService: RegisterService

    @MockBean
    private lateinit var loginService: LoginService

    @Test
    @DisplayName("회원가입 호출")
    fun testSignup() {
        val registerInfo = RegisterInfo(
            userName = "test-user",
            userEmail = "test@test.com",
            password = "1234@@",
            passwordAgain = "1234@@"
        )

        given(registerService.run(registerInfo)).willReturn(mapOf())

        val request = post("/auth/signup").contentType(MediaType.APPLICATION_JSON).content(Gson().toJson(registerInfo))
        mockMvc.perform(request).andExpect(status().isCreated)
    }

    @Test
    @DisplayName("로그인 호출")
    fun testLogin() {
        val loginInfo = LoginInfo(
            userEmail = "test@test.com",
            password = "1234@@"
        )

        given(loginService.run(loginInfo)).willReturn(mapOf())

        val request = post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(Gson().toJson(loginInfo))
        mockMvc.perform(request).andExpect(status().isOk)
    }
}
