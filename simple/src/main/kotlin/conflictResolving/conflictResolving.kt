package conflictResolving

import io.kabu.annotations.GlobalPattern

// Example-016

// declarations inside inferred lambda go into its scope (scope narrowing)
@GlobalPattern("name * age - { occupation * income }")
fun printPersonInfo(occupation: String, income: Int, name: String, age: Int) {
    println("Person '$name'($age) is '$occupation'($income X)")
}

// declarations go into one shared scope
@GlobalPattern("name % age - occupation % income")
fun printPersonInfo2(occupation: String, income: Int, name: String, age: Int) {
    println("Person '$name'($age) is '$occupation'($income X)")
}

fun main() {
    // below are two equal expressions (with '*' syntax):
    "Adam" * 20 - { "Physicist" * 1000 }
    "Adam".times(age = 20) - { "Physicist".times(income = 1000) } // parameter names are conserved as conflict was avoided

    // below are two equal expressions (with '%' syntax):
    "Adam" % 20 - "Physicist" % 1000
    "Adam".rem(int = 20) - "Physicist".rem(int = 1000) // parameter names aren't conserved as conflict was resolved
}
