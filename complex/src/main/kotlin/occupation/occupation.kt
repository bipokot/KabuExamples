package occupation

import io.kabu.annotation.ContextCreator
import io.kabu.annotation.Pattern
import io.kabu.annotation.LocalPattern

// Example-017

class PersonBuilder @ContextCreator("personBuilder") constructor() {

    var occupation: String? = null

    @LocalPattern("occupation = value")
    fun setOccupationValue(value: String) {
        occupation = value
    }
}

@Pattern("person @Extend(context = personBuilder(), parameter = builder) {}")
fun printPerson(builder: PersonBuilder) {
    println("Person: ${builder.occupation}")
}

fun main() {
    person {
        occupation = "Software developer"
//        println(occupation) // compiler error as pattern "occupation = value" provides a setter only
    }

    /* Prints:
    Person: Software developer
     */
}
