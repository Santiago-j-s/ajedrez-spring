package com.example.ajedrez

import javax.persistence.*

@Entity
class User(
    var username: String,
    var name: String,

    @Id @GeneratedValue var id: Long? = null
) {
    @ManyToOne
    lateinit var role: Role
}

@Entity
class Role(
    var name: String,
    @Id @GeneratedValue var id: Long? = null
) {
    @OneToMany(mappedBy = "role")
    val users: List<User> = ArrayList<User>()
}