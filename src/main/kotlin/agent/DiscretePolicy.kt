package agent

import environment.Action
import environment.EnvironmentModel

class DiscretePolicy(val exploration : (Action) -> Action.ActionType?,
                     val selection : (EnvironmentModel, (EnvironmentModel) -> ActionMap) -> Action.ActionType,
                     val adaptFunction : (Double, Double, Double) -> Double,
                     val actions : Action, weight: Double=1.0) : Policy(weight) {

    private val policyMap : MutableMap<EnvironmentModel, ActionMap> = mutableMapOf()

    override fun act(model: EnvironmentModel): Action.ActionType =
            exploration(actions) ?: selection(model, {model -> actionValues(model)})


    override fun adapt(episodes: List<Episode>) {
        for ((model, action, reward) in episodes) {
            val actionMap = policyMap.getOrPut(model) {ActionMap(actions)}
            val currentValue = actionMap.actionMap.getOrPut(action){0.0}
            val nextValue = actionMap.actionMap.getOrPut(selection(model, { model -> actionValues(model)})){0.0}
            actionMap.actionMap[action] = adaptFunction(reward, currentValue, nextValue)
        }
    }

    override fun actionValues(model: EnvironmentModel) : ActionMap = policyMap.getOrPut(model) {ActionMap(actions)}

}
