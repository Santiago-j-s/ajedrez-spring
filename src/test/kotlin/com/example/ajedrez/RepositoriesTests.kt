package com.example.ajedrez

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val roleRepository: RoleRepository
) {
    @Test
    fun `When findByIdOrNull then return User`() {
        val admin = User("admin", "Santiago Santana")
        entityManager.persist(admin)

        entityManager.flush()

        val userFound = userRepository.findByUsername("admin")
        assertThat(userFound).isEqualTo(admin)
    }

    @Test
    fun `When findByName then return Role`() {
        val user = User("admin", "Santiago Santana")
        entityManager.persist(user)
        val role = Role("admin role", user)
        entityManager.persist(role)

        entityManager.flush()

        val roleFound = roleRepository.findByName("admin role")
        assertThat(roleFound).isEqualTo(role)
    }

    @Test
    fun `When findByUserUsername then return List of Role`() {
        val user = User("admin", "Santiago Santana")
        entityManager.persist(user)
        val role = Role("admin role", user)
        entityManager.persist(role)

        entityManager.flush()

        val roles = roleRepository.findByUserUsername("admin")
        assertThat(roles).isInstanceOf(List::class.java)
        assertThat(roles[0]).isEqualTo(role)
    }
}