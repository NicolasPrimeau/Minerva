package Agent.blocks

import Environment.Action
import java.security.SecureRandom
import java.util.*

class ExplorationProvider(private val action: Action) {

    fun getStochasticExploration(chanceOfExploration: Double) : () -> Action? {
        val r : Random = SecureRandom()
        return {
                if (r.nextDouble() < chanceOfExploration) {
                    Action.values()[r.nextInt(Action.values().size)]
                } else {
                    null
                }
            }
    }
}