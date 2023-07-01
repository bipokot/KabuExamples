package team

import io.kabu.annotation.ContextCreator
import io.kabu.annotation.Pattern
import io.kabu.annotation.LocalPattern

// Example-007

data class Player(
    val name: String,
    val number: Int
)

data class Trophy(
    val name: String,
    val times: Int
)

data class FootballTeam(
    val name: String,
    val isChampion: Boolean,
    val players: List<Player>,
    val trophies: List<Trophy>
)

class PlayersBuilder @ContextCreator("playersBuilder") constructor() {
    val players = mutableListOf<Player>()

    @LocalPattern("name - number")
    fun addPlayer(name: String, number: Int) {
        players.add(Player(name, number))
    }
}

class FootballTeamBuilder @ContextCreator("footballTeamBuilder") constructor() {

    val trophies = mutableListOf<Trophy>()
    var isChampion = false
    val players = mutableListOf<Player>()

    @LocalPattern("champion")
    fun champion() {
        isChampion = true
    }

    @LocalPattern("!number - trophy")
    fun addTrophy(trophy: String, number: Int) {
        trophies.add(Trophy(trophy, number))
    }

    @LocalPattern("has outstanding players @Extend(context = playersBuilder(), parameter = builder) {}")
    fun addPlayers(builder: PlayersBuilder) {
        players.addAll(builder.players)
    }
}

@Pattern("football team name @Extend(context = footballTeamBuilder(), parameter = builder) {}")
fun footballTeam(name: String, builder: FootballTeamBuilder): FootballTeam {
    return FootballTeam(name, builder.isChampion, builder.players, builder.trophies)
}

fun main() {
    val team = football team "Tigers" {
        champion
        
        !3 - "Super League Champions"
        !26 - "National League"

        has outstanding players {
            "John Smith" - 10
            "Enrique Hernandez" - 2
        }
    }
    println(team)

    /* Prints:
    FootballTeam(name=Tigers, isChampion=true, players=[Player(name=John Smith, number=10), Player(name=Enrique Hernandez, number=2)], trophies=[Trophy(name=Super League Champions, times=3), Trophy(name=National League, times=26)])
     */
}
