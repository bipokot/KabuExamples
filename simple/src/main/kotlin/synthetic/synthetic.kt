package synthetic

import io.kabu.annotation.Pattern

// Example-003

@Pattern("x X string X x")
fun glorify(string: String) = string

fun main() {
    val winners = x X "CyberMaster Athletes" X x
    println(winners)
}
