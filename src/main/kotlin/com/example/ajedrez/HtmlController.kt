package com.example.ajedrez

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController(
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository
) {
    @GetMapping("/")
    fun mainPage(model: Model): String {
        model["title"] = "Ajedrez"
        model["users"] = userRepository.findAll()
        return "ajedrez"
    }
}
