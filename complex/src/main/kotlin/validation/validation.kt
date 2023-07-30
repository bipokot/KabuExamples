package validation

import io.kabu.annotation.Context
import io.kabu.annotation.LocalPattern
import io.kabu.annotation.Pattern

// Example-011

class User(
    val name: String,
    val age: Int,
)

class ValidationCondition(
    val condition: User.() -> Boolean,
    val messageSupplier: ((User) -> String)?,
)

class ValidationConditions(val conditions: List<ValidationCondition>)

@Context("builder")
class ConditionsBuilder {
    val list = mutableListOf<ValidationCondition>()

    @LocalPattern("check (condition) - (message)")
    fun add(condition: User.() -> Boolean, message: ((User) -> String)?) {
        list.add(ValidationCondition(condition, message))
    }
}

@Pattern("conditions @Extend(context = builder(), parameter = context) {}")
fun defineConditions(context: ConditionsBuilder) = ValidationConditions(context.list)

@Pattern("failed rule { user > conditions }")
fun getMessageOfFailedRuleOrNull(conditions: ValidationConditions, user: User): String? {
    return conditions.conditions
        .firstOrNull {
            val condition = it.condition
            !user.condition()
        }
        ?.messageSupplier
        ?.invoke(user)
}

@Pattern("validate { user > conditions }")
fun validateUserWithGivenConditions(conditions: ValidationConditions, user: User) {
    conditions.conditions
        .firstOrNull { !it.condition(user) }
        ?.let {
            throw RuntimeException(it.messageSupplier?.invoke(user))
        }
}

fun main() {
    val conditions = conditions {
        check { name.matches(Regex("[^\\d]+")) } - { "User name must not contain digits: ${it.name}" }
        check { name.length > 1 } - null
        check { age >= 18 } - { "User must be at least 18 years old: ${it.name}" }
    }

    val max = User("Max", 17)
    val a = User("9", 31)

    println(failed rule { max > conditions })

    println("The following exception is expected:")
    validate { a > conditions }
}
