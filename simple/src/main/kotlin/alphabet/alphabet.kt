package alphabet

import io.kabu.annotations.GlobalPattern
import io.kabu.runtime.InclusionInfo

// Example-019

@GlobalPattern("a{{ b ..< c } !in -d[e, +-{f}[g][{{{}..{h.i = j}}}], k(l){ m += n} + !{o * -p(q {r[s.t.u] = v w x})}]} / y + z")
fun alphabet(
    b: Int,
    inclusion: InclusionInfo,
    e: String,
    g: Int,
    j: String,
    m: Int,
    r: Int,
    s: Int,
    v: String,
    z: Int,
) {
    listOf(b, inclusion, e, g, j, m, r, s, v, z).forEach(::println)
}

fun main() {
    a{{ 2 ..< c } !in -d["3", +-{f}[5][{{{}..{h.i = "7"}}}], k(l){ 11 += n } + !{o * -p(q {13[17.t.u] = "19" w x})}]} / y + 23

    /* Prints:
    2
    NOT_IN
    3
    5
    7
    11
    13
    17
    19
    23
     */
}
