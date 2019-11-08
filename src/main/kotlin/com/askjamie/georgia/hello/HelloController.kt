package com.askjamie.georgia.hello

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val environment: Environment) {

    @Value("\${image.version:development}")
    private lateinit var imageVersion: String

    @GetMapping("/hello")
    fun hello(@RequestParam(defaultValue = "World") name: String) = "hello, $name!"

    @GetMapping("/profile")
    fun profile() = mapOf(
        "build.env" to environment.activeProfiles,
        "image.version" to imageVersion
    )
}
