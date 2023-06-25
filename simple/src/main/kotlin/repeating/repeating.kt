package repeating

import io.kabu.annotations.GlobalPattern

// Example-002

@GlobalPattern("string * count")
fun repeatString(count: Int, string: String) = buildString {
    repeat(count) { append(string) }
}

fun main() {
    println("abc" * 3)

    /* Prints:
    abcabcabc
    */
}
