package gibberish

import io.kabu.annotation.Pattern

// Example-018

@Pattern("ᘤ [ᘎ, a, +ᒣ {!b{ᐳƧ}*c}] - ϾϿ(-d[Ⲷ]){ e Ⴖ Ϟ % ᘃ(ᗏ) ᗊ -ᓬ[f] }")
fun gibberish(a: String, b: String, c: String, d: String, e: String, f: String) {
    println(a + b + c + d + e + f)
}

fun main() {
    ᘤ [ᘎ, "K", +ᒣ {!"o"{ᐳƧ}*"t"}] - ϾϿ(-"l"[Ⲷ]){ "i" Ⴖ Ϟ % ᘃ(ᗏ) ᗊ -ᓬ["n"] }

    /* Prints:
    Kotlin
     */
}
