package functional

import io.kabu.annotation.Pattern

// Example-006

@Pattern("block onlyIf condition")
fun onlyIf(block: () -> String, condition: Boolean) = if (condition) block() else null

fun main() {
    println({ println("evaluating 'abc'"); "abc" } onlyIf true)
    println({ println("evaluating 'def'"); "def" } onlyIf false)

    /* Prints:
    evaluating 'abc'
    abc
    null
    */
}
