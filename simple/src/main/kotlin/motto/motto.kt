package motto

import io.kabu.annotation.Pattern

// Example-001

@Pattern("The.Declarative[!way] /= to * create { +{ a > DSL } } - message")
fun motto(message: String) = println(message)

fun main() {
    The.Declarative[!way] /= to * create { +{ a > DSL } } - "👍"
}
