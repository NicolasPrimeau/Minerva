package main.kotlin.Agent.blocks

import main.kotlin.Environment.Action
import java.security.SecureRandom
import java.util.*

class ExplorationProvider() {

    fun getStochasticExploration(chanceOfExploration: Double) : (Array<Action>) -> Action? {
        val r = SecureRandom()
        return { actions ->
                if (r.nextDouble() < chanceOfExploration) {
                    actions[r.nextInt(actions.size)]
                } else {
                    null
                }
            }
    }

    fun getMonotonicallyDecreasingExploration(chanceOfExploration: Double, decreasePerExploration: Double):
            (Array<Action>) -> Action? {
        val r = SecureRandom()
        var prob = chanceOfExploration
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