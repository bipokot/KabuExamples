@file:Suppress("EnumEntryName")

package inverted

import io.kabu.annotation.ContextCreator
import io.kabu.annotation.Pattern
import io.kabu.annotation.LocalPattern
import inverted.OnOff.*

// Example-010

/**
 * Simple way to use lambda with receiver and not prefixing it with some keyword ( 'foo { ... }' )
 * is to use 'in' operator (the type of the right side is determined first)
 */

enum class OnOff {
    on, off
}

class Builder @ContextCreator("ctx") constructor() {

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
}
