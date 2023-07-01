package taskList

import io.kabu.annotation.ContextCreator
import io.kabu.annotation.Pattern
import io.kabu.annotation.LocalPattern

// Example-015

class TaskListBuilder @ContextCreator("taskBuilder") constructor(listName: String, place: String) {

    init {
        println("Building '$listName' for '$place'...")
    }

    val list = mutableListOf<String>()

    @LocalPattern("- todo")
    fun addTodo(todo: String) {
        list += todo
    }
}

@Pattern("listName * place - @Extend(context = taskBuilder(listName, place), parameter = builder) {}")
fun printTaskList(listName: String, place: String, builder: TaskListBuilder) {
    println("$listName tasks at $place:")
    builder.list.forEach { println("[ ] $it") }
}

fun main() {
    "Maintenance" * "Home" - {
        -"Check plants"
        -"Start washing machine"
        -"Fix broken door"
    }

    /* Prints:
    Building 'Maintenance' for 'Home'...
    Maintenance tasks at Home:
    [ ] Check plants
    [ ] Start washing machine
    [ ] Fix broken door
     */
}
