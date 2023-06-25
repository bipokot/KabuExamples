package functional

import io.kabu.annotations.GlobalPattern

// Example-006

@GlobalPattern("block onlyIf condition")
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
