package json

import io.kabu.annotations.ContextCreator
import io.kabu.annotations.GlobalPattern
import io.kabu.annotations.LocalPattern

// Example-008

@DslMarker
annotation class JsonDsl

open class JsonNode

data class JsonObject(
    val properties: Map<String, Any>
) : JsonNode() {
    override fun toString() = properties.toString()
}

data class JsonArray(
    val array: List<Any>
) : JsonNode() {
    override fun toString() = array.toString()
}

@JsonDsl
class JsonObjectBuilder @ContextCreator("objectBuilder") constructor() {

    val map = mutableMapOf<String, Any>()

    @LocalPattern("propertyName - value")
    fun addObjectProperty(propertyName: String, value: Any) {
        map[propertyName] = value
    }
}

@JsonDsl
class JsonArrayBuilder @ContextCreator("arrayBuilder") constructor() {

    val list = mutableListOf<Any>()

    @LocalPattern("+ value")
    fun addArrayElement(value: Any) {
        list.add(value)
    }
}

@GlobalPattern("jsonObject @Extend(context = objectBuilder(), parameter = objectBuilder) {}")
fun createJsonObject(objectBuilder: JsonObjectBuilder) = JsonObject(objectBuilder.map)

@GlobalPattern("jsonArray @Extend(context = arrayBuilder(), parameter = arrayBuilder) {}")
fun createJsonArray(arrayBuilder: JsonArrayBuilder) = JsonArray(arrayBuilder.list)


fun main() {
    val a = jsonObject {
        "first" - jsonObject {
            "inner" - 101
        }
        "second" - jsonArray {
            +"a"
            +jsonArray {
                +"1"
                +jsonObject {
                    "deepest" - 1
                    "object" - 2
                }
            }
            +"c"
        }
    }
    println(a)

    /* Prints:
    {first={inner=101}, second=[a, [1, {deepest=1, object=2}], c]}
     */
}
