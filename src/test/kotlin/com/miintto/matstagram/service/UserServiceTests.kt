package com.miintto.matstagram.service

import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.domain.projection.AuthUserSummary
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
import java.time.LocalDateTime

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTests {
    data class AuthUserSummaryImp(
        override val id: Long,
        override val userName: String,
        override val userEmail: String,
        override val userPermission: String,
        override val isActive: Boolean,
        override val profileImage: String?,
        override val createdDtm: LocalDateTime,
        override val lastLoginDtm: LocalDateTime?
    ) : AuthUserSummary

    @MockBean
    lateinit var authUserRepository: AuthUserRepository

    @Autowired
    private lateinit var userService: UserService

    @Test
    @DisplayName("사용자 조회")
    fun testGetUser() {
        val userId = 1L
        val user = AuthUserSummaryImp(
            id = userId,
            userName = "test-user",
            userEmail = "test@test.com",
            userPermission = "NORMAL",
            isActive = true,
            profileImage = null,
            createdDtm = LocalDateTime.now(),
            lastLoginDtm = null
        )
        given(authUserRepository.findProjectedById(userId)).willReturn(user)

        userService.getUserById(userId)

        verify(authUserRepository).findProjectedById(userId)
    }

    @Test
    @DisplayName("존재하지 않는 사용자")
    fun testUserNotFound() {
        val userId = 2L
        given(authUserRepository.findProjectedById(userId)).willReturn(null)

        assertThrows<ApiException> {
            userService.getUserById(userId)
        }
    }
}
