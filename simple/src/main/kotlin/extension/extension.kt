package extension

import io.kabu.annotations.GlobalPattern

// Example-012

class Scope {
    val isDebug = true

    fun logMessage(message: String) {
        println(message)
    }
}

@GlobalPattern("debug (messageBuilder)")
fun Scope.debugPostponed(messageBuilder: () -> String) {
    if (isDebug) logMessage(messageBuilder())
}

fun main() {
    with(Scope()) {
        debug { "message" }
    }
}
