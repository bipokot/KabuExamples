package command

import io.kabu.annotation.Pattern

// Example-004

@Pattern("shell > command")
fun executeCommand(command: String) {
    println("Executing a command: $command")
    val process = Runtime.getRuntime().exec(command)
    val output = process.inputStream.bufferedReader().readText()
    println(output)
}

fun main() {
    shell > "whoami"
}
