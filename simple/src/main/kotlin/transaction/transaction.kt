package transaction

import io.kabu.annotation.Pattern
import io.kabu.runtime.RankingComparisonInfo
import io.kabu.runtime.RankingComparisonInfo.GREATER
import io.kabu.runtime.RankingComparisonInfo.LESS

// Example-005

data class User(
    var balance: Int
)

@Pattern("send[amount] { user1 > user2 }")
fun transaction(amount: Int, user1: User, rank: RankingComparisonInfo, user2: User) {

    fun moveMoney(amount: Int, from: User, to: User) {
        // dumb transaction implementation, don't try this at work
        from.balance -= amount
        to.balance += amount
    }

    when (rank) {
        GREATER -> moveMoney(amount, from = user1, to = user2)
        LESS -> moveMoney(amount, from = user2, to = user1)
    }
}


fun main() {
    val alice = User(1000)
    val bob = User(800)
    
    println("Alice: $alice")
    println("Bob: $bob")

    send[200] { alice > bob }
    send[100] { alice < bob }

    println("Alice: $alice")
    println("Bob: $bob")

    /* Prints:
    Alice: User(balance=1000)
    Bob: User(balance=800)
    Alice: User(balance=900)
    Bob: User(balance=900)
     */
}
