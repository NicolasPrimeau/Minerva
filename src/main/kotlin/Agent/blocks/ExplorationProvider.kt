package Agent.blocks

import Environment.Action
import java.security.SecureRandom

class ExplorationProvider() {

    fun getStochasticExploration(chanceOfExploration: Double) : (Array<Action>) -> Action? {
        return getMonotonicallyDecreasingStochasticExploration(chanceOfExploration, 0.0)
    }

    fun getMonotonicallyDecreasingStochasticExploration(chanceOfExploration: Double, decreasePerExploration: Double):
            (Array<Action>) -> Action? {
        val r = SecureRandom()
        var prob = if (chanceOfExploration >= 0) chanceOfExploration else 0.0
        return { actions ->
            if (r.nextDouble() < prob) {
                prob = arrayOf(prob - decreasePerExploration, 0.0).max() ?: 0.0
                actions[r.nextInt(actions.size)]
            } else {
                null
            }
        }
    }
}