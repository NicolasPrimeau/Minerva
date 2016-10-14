package Agent

import Environment.Action
import Environment.EnvironmentModel
import java.security.SecureRandom
import java.util.*

class DiscretePolicy(val exploration : (Action) -> Action?,
                     val selection : (Map<Action, Double>) -> Action,
                     val adaptFunction : (currentValue : Double, nextValue: Double) -> Double,
                     private val actions: Action, weight: Double) : Policy(weight) {

    private class ActionMap(actions: Action) {
        val map : MutableMap<Action, Double> = mutableMapOf()
            get

        init {
            val random = SecureRandom()
            actions.let { action -> map[action] = random.nextDouble()}
        }
    }

    private val policyMap : MutableMap<EnvironmentModel, ActionMap> = mutableMapOf()

    override fun act(model: EnvironmentModel): Action {
        return exploration(actions) ?: selection(
                HashMap<Action, Double>((policyMap.getOrPut(model) {ActionMap(actions)}).map))
    }

    override fun adapt(episodes: List<Episode>) {
        for (episode in episodes) {
            val actionMap : ActionMap = (policyMap.getOrPut(episode.model) {ActionMap(actions)})
            val currentValue: Double = actionMap.map.getOrPut(episode.action){0.0}
            val nextValue: Double = actionMap.map.getOrPut(selection(
                    HashMap<Action, Double>((policyMap.getOrPut(episode.model) {ActionMap(actions)}).map))){0.0}
            actionMap.map[episode.action] = adaptFunction(currentValue, nextValue)
        }
    }
}
