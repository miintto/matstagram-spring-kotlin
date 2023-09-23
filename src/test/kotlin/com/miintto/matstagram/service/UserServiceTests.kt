package com.miintto.matstagram.service

import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.domain.AuthUser
import com.miintto.matstagram.domain.repository.AuthUserRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import java.util.Optional

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTests {

    @MockBean
    lateinit var authUserRepository: AuthUserRepository

    @Autowired
    private lateinit var userService: UserService

    @Test
    @DisplayName("사용자 조회")
    fun testGetUser() {
        val userId = 1L
        val user = AuthUser(userName = "test-user", userEmail = "test@test.com", password = "encrypted-string")
        given(authUserRepository.findById(userId)).willReturn(Optional.of(user))

        userService.getUserById(userId)

        verify(authUserRepository).findById(userId)
    }

    @Test
    @DisplayName("존재하지 않는 사용자")
    fun testUserNotFound() {
        val userId = 2L
        given(authUserRepository.findById(userId)).willReturn(Optional.empty())

        assertThrows<ApiException> {
            userService.getUserById(userId)
        }
    }
}
