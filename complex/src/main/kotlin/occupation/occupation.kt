package occupation

import io.kabu.annotation.Context
import io.kabu.annotation.LocalPattern
import io.kabu.annotation.Pattern

// Example-017

@Context("personBuilder")
class PersonBuilder {

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
