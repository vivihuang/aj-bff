package com.askjamie

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class Application

@SuppressWarnings("SpreadOperator")
fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
