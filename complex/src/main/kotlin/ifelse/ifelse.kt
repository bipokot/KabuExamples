package ifelse

import io.kabu.annotation.Context
import io.kabu.annotation.LocalPattern
import io.kabu.annotation.Pattern

// Example-009

@Context("actions")
class Actions {
    val trueActions = mutableListOf<() -> Unit>()
    val falseActions = mutableListOf<() -> Unit>()

    @LocalPattern("+action")
    fun addTrueAction(action: () -> Unit) {
        trueActions += action
    }

    @LocalPattern("-action")
    fun addFalseAction(action: () -> Unit) {
        falseActions += action
    }
}

@Pattern("condition @Extend(context = actions(), parameter = actions) {}")
fun ifElse(condition: Boolean, actions: Actions) {
    val actionsToExecute = if (condition) actions.trueActions else actions.falseActions
    actionsToExecute.forEach { it() }
}

fun main() {
    val isOperationSuccessful = false
    isOperationSuccessful {
        + { println("show message 'Success'") }
        - { println("show message 'Failure'") }

        + { println("activate button 'Close'") }
        - { println("activate button 'Repeat'") }
    }

    /* Prints:
    show message 'Failure'
    activate button 'Repeat'
     */
}
