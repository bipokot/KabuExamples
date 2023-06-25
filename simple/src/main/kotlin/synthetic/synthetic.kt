package synthetic

import io.kabu.annotations.GlobalPattern

// Example-003

@GlobalPattern("x X string X x")
fun glorify(string: String) = string

fun main() {
    val winners = x X "CyberMaster Athletes" X x
    println(winners)
}
