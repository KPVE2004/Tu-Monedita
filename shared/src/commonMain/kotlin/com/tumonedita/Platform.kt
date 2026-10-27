package com.tumonedita

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform