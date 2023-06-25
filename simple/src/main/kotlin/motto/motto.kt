package motto

import io.kabu.annotations.GlobalPattern

// Example-001

@GlobalPattern("The.Declarative[!way] /= to * create { +{ a > DSL } } - message")
fun motto(message: String) = println(message)

fun main() {
    The.Declarative[!way] /= to * create { +{ a > DSL } } - "👍"
}
