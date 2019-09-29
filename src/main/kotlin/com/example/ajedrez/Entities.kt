package com.example.ajedrez

import javax.persistence.*

@Entity
class User(
    var username: String,
    var name: String,
    @Id @GeneratedValue var id: Long? = null
)

@Entity
class Role(
    var name: String,
    @ManyToOne var user: User,
    @Id @GeneratedValue var id: Long? = null
)