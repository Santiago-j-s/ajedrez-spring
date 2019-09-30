package com.example.ajedrez

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.stringContainsInOrder
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HtmlControllerTests(
        @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    private lateinit var userRepository: UserRepository
    @MockkBean
    private lateinit var roleRepository: RoleRepository

    @Test
    fun `List users`() {
        val user1 = User("jane", "Jane Doe")
        val user2 = User("john", "John Doe")
        val user3 = User("santiago", "Santiago Santana")

        val adminRole = Role("admin role")
        val userRole = Role("user role")

        user1.role = userRole
        user2.role = userRole
        user3.role = adminRole

        every { userRepository.findAll() } returns listOf(user1, user2, user3)

        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(
                        content().string(stringContainsInOrder(listOf(
                                "<td>jane</td>",
                                "<td>Jane Doe</td>",
                                "<td>user role</td>",
                                "<td>john</td>",
                                "<td>John Doe</td>",
                                "<td>user role</td>",
                                "<td>santiago</td>",
                                "<td>Santiago Santana</td>",
                                "<td>admin role</td>"
                        )))
                )
    }
}