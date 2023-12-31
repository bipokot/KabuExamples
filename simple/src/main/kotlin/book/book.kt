package book

import io.kabu.annotation.Pattern

// Example-014

@Pattern("print book name[author / year] .. description")
fun printBook(name: String, description: String, year: Int, author: String) {
    println("'$name' by $author ($year)\n'$description'")
}

fun main() {
    print book "About Nothing"["Smart Person" / 2011].."The best book in the world"

    /* Prints:
    'About Nothing' by Smart Person (2011)
    'The best book in the world'
     */
}
