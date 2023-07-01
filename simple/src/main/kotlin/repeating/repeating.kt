package repeating

import io.kabu.annotation.Pattern

// Example-002

@Pattern("string * count")
fun repeatString(count: Int, string: String) = buildString {
    repeat(count) { append(string) }
}

fun main() {
    println("abc" * 3)

    /* Prints:
    abcabcabc
    */
}
