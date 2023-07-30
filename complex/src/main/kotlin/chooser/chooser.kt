package chooser

import io.kabu.annotation.Context
import io.kabu.annotation.LocalPattern
import io.kabu.annotation.Pattern

// Example-020

@Context("chooser")
class ChooserContext(val clause: Boolean) {

    @LocalPattern("a otherwise b")
    fun <T> otherwise(a: T, b: T) = if (clause) a else b
}

@Pattern("chooseBy(clause) @Extend(context = chooser(clause), parameter = context) {}")
fun choose(clause: Boolean, context: ChooserContext) = Unit

fun main() {
    val isSuccess = true
    chooseBy(isSuccess) {
        val titleText = "Success" otherwise "Error"
        val buttonText = "Close" otherwise "Try again"
        val statusText = "Completed" otherwise "Failed"

        println("UI: title='$titleText', button='$buttonText', status='$statusText'")
    }

    /* Prints:
    UI: title='Success', button='Close', status='Completed'
     */
}
