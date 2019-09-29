package com.example.ajedrez

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User
}

interface RoleRepository : CrudRepository<Role, Long> {
    fun findByName(name: String): Role
    fun findByUserUsername(username: String): List<Role>
}