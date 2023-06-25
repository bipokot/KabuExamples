package command

import io.kabu.annotations.GlobalPattern

// Example-004

@GlobalPattern("shell > command")
fun executeCommand(command: String) {
    println("Executing a command: $command")
    val process = Runtime.getRuntime().exec(command)
    val output = process.inputStream.bufferedReader().readText()
    println(output)
}

fun main() {
    shell > "whoami"
}
