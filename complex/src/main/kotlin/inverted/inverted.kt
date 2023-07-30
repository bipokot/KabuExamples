@file:Suppress("EnumEntryName")

package inverted

import inverted.OnOff.*
import io.kabu.annotation.Context
import io.kabu.annotation.LocalPattern
import io.kabu.annotation.Pattern

// Example-010

/**
 * Simple way to use lambda with receiver and not prefixing it with some keyword ( 'foo { ... }' )
 * is to use 'in' operator (the type of the right side is determined first)
 */

enum class OnOff {
    on, off
}

@Context("ctx")
class Builder {

    var acceleration: OnOff = off

    @LocalPattern("acceleration > enabled")
    fun foo(enabled: OnOff) {
        acceleration = enabled
    }
}

@Pattern("@Extend(context = ctx(), parameter = builder) {} in case of fire")
fun bar(builder: Builder) {
    println("In case of fire acceleration is set to: ${builder.acceleration}")
}

fun main() {
    {
        acceleration > on
    } in case of fire

    /* Prints:
    In case of fire acceleration is set to: on
     */
}
