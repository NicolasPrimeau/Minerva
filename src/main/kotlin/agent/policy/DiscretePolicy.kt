package agent.policy

import agent.ActionMap
import agent.Episode
import agent.ObjectiveEvaluator
import environment.Action
import environment.EnvironmentModel
import environment.Objective

class DiscretePolicy(config: PolicyConfiguration) : Policy(config.weight) {

    private val exploration = config.exploration
    private val selection = config.selection
    private val actions = config.actions
    private val target = config.target
    private val adaptFunction = config.adaptFunction
    private val policyMap : MutableMap<EnvironmentModel, ActionMap> = mutableMapOf()

    override fun act(model: EnvironmentModel): Action.ActionType =
            exploration(actions) ?: selection(model, {model -> actionValues(model)[target]!!})

    override fun adapt(objectives: Map<Objective.ObjectiveType, ObjectiveEvaluator>) {
        val episodes = if (objectives[target] != null) objectives[target]!!.getEpisodes() else listOf<Episode>()
        for ((model, action, reward) in episodes) {
            val actionMap = policyMap.getOrPut(model) { ActionMap(actions) }
            val currentValue = actionMap.actionMap.getOrPut(action){0.0}
            val nextValue = actionMap.actionMap.getOrPut(selection(model, { model -> actionValues(model)[target]!!})){0.0}
            actionMap.actionMap[action] = adaptFunction(reward, currentValue, nextValue)
        }
        if (objectives[target] != null) {
            objectives[target]!!.clearEpisodes()
        }
    }

    override fun actionValues(model: EnvironmentModel) : Map<Objective.ObjectiveType, ActionMap> =
            mapOf(Pair(target, policyMap.getOrPut(model) { ActionMap(actions) }))

}
