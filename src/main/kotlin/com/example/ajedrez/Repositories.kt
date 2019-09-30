package com.example.ajedrez

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User
    fun findByRoleName(name: String): List<User>
}

interface RoleRepository : CrudRepository<Role, Long> {
    fun findByName(name: String): Role
    fun findByUsersUsername(username: String): Role
}