package ifelse2

import io.kabu.annotation.Context
import io.kabu.annotation.LocalPattern
import io.kabu.annotation.Pattern

// Example-013

@Context("actionsContext")
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

    @LocalPattern("the great truth in action")
    fun addTrueActionYoda(action: () -> Unit) = addTrueAction(action)

    @LocalPattern("the miserable lie in action")
    fun addFalseActionYoda(action: () -> Unit) = addFalseAction(action)

    @LocalPattern("case of action")
    fun createAction(action: () -> Unit) = action
}

@Pattern("condition @Extend(context = actionsContext(), parameter = actions) {}")
fun ifElse(condition: Boolean, actions: Actions) {
    (if (condition) actions.trueActions else actions.falseActions).forEach { it() }
}

@Pattern("condition Yoda said @Extend(context = actionsContext(), parameter = actions) {}")
fun yodaIfElse(condition: Boolean, actions: Actions) = ifElse(condition, actions)


fun main() {
    val theEarthIsFlat = true
    theEarthIsFlat {
        + { println("statement is true") }

        - { println("statement is false") }

        + { println("Of course it is flat! Look around, don't be fooled by government!") }
    }

    theEarthIsFlat Yoda said {
        the great truth in case of { println("statement is true") }
        the miserable lie in case of { println("statement is false") }
    }

    /* Prints:
    statement is true
    Of course it is flat! Look around, don't be fooled by government!
    statement is true
     */
}
