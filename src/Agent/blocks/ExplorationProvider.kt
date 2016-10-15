package Agent.blocks

import Environment.Action
import java.security.SecureRandom
import java.util.*

class ExplorationProvider() {

    fun getStochasticExploration(chanceOfExploration: Double) : (Array<Action>) -> Action? {
        val r : Random = SecureRandom()
        return { actions ->
                if (r.nextDouble() < chanceOfExploration) {
                    actions[r.nextInt(actions.size)]
                } else {
                    null
                }
            }
    }
}