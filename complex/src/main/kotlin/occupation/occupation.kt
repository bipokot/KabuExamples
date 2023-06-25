package occupation

import io.kabu.annotations.ContextCreator
import io.kabu.annotations.GlobalPattern
import io.kabu.annotations.LocalPattern

// Example-017

class PersonBuilder @ContextCreator("personBuilder") constructor() {

    var occupation: String? = null

    @LocalPattern("occupation = value")
    fun setOccupationValue(value: String) {
        occupation = value
    }
}

@GlobalPattern("person @Extend(context = personBuilder(), parameter = builder) {}")
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
