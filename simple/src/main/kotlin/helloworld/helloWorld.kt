package helloworld

import io.kabu.annotations.GlobalPattern

// Example-000

@GlobalPattern("hello")
fun helloWorld() {
    println("Hello, World!")
}

fun main() {
    hello

    /* Prints:
    Hello, World!
     */
}
