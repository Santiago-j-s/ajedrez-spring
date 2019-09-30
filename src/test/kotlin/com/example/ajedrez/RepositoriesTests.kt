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
    fun `When findByRoleName then return List of User`() {
        val user1 = User("user1", "Santiago Santana")
        val user2 = User("user2", "John Doe")

        val role = Role("user role")
        user1.role = role
        user2.role = role

        entityManager.persist(role)
        entityManager.persist(user1)
        entityManager.persist(user2)
        entityManager.flush()

        val users = userRepository.findByRoleName("user role")

        assertThat(users).isInstanceOf(List::class.java)
        assertThat(users.contains(user1))
        assertThat(users.contains(user2))
    }

    @Test
    fun `When findByName then return Role`() {
        val role = Role("admin role")

        entityManager.persist(role)
        entityManager.flush()

        val roleFound = roleRepository.findByName("admin role")
        assertThat(roleFound).isEqualTo(role)
    }

    @Test
    fun `When findByUserUsername then return Role`() {
        val role = Role("admin role")
        entityManager.persist(role)

        val user = User("admin", "Santiago Santana")
        user.role = role

        entityManager.persist(user)
        entityManager.flush()

        val roleFound = roleRepository.findByUsersUsername("admin")
        assertThat(roleFound).isEqualTo(role)
    }
}