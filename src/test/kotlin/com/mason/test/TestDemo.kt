package com.mason.test

import java.time.Instant

/**
 * Created by mwu on 2020/7/6
 */
fun main() {
    val now = Instant.now().plusSeconds(10 * 365 * 24 * 3600L)
    println(now)
}