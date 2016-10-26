package agent.blocks

import environment.Action
import java.security.SecureRandom

class ExplorationProvider() {

    fun getStochasticExploration(chanceOfExploration: Double) : (Action) -> Action.ActionType? {
        return getMonotonicallyDecreasingStochasticExploration(chanceOfExploration, 0.0)
    }

    fun getMonotonicallyDecreasingStochasticExploration(chanceOfExploration: Double, decreasePerExploration: Double):
            (Action) -> Action.ActionType? {
        val r = SecureRandom()
        var prob = if (chanceOfExploration >= 0) chanceOfExploration else 0.0
        return { actions ->
            if (r.nextDouble() < prob) {
                prob = arrayOf(prob - decreasePerExploration, 0.0).max() ?: 0.0
                actions.getType(r.nextInt(actions.numActions))
            } else {
                null
            }
        }
    }
}