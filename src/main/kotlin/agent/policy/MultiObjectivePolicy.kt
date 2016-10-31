package agent.policy

import agent.ActionMap
import agent.ObjectiveEvaluator
import environment.Action
import environment.EnvironmentModel
import environment.Objective

class MultiObjectivePolicy(objectives: Objective,
                           val policySelection: (Map<Objective.ObjectiveType, Policy>, EnvironmentModel) -> Action.ActionType,
                           val configurations: Map<Objective.ObjectiveType, PolicyConfiguration>) : Policy() {

    val policies = objectives.getAllObjectiveTypes().associateBy({it}, {
        if (configurations.containsKey(it)) {
            DiscretePolicy(configurations[it]!!)
        } else {
            throw IllegalStateException("Need configuration for all objectives!")
        }
    })

    override fun act(model: EnvironmentModel) : Action.ActionType {
        return policySelection(policies, model)
    }

    fun setObjectiveWeights(weights: Map<Objective.ObjectiveType, Double>) {
        weights.forEach { w -> policies[w.key]?.weight = w.value }
    }

    override fun adapt(objectives: Map<Objective.ObjectiveType, ObjectiveEvaluator>) {
        objectives.forEach { p -> policies[p.key]?.adapt(objectives) }
    }

    override fun actionValues(model: EnvironmentModel) : Map<Objective.ObjectiveType, ActionMap> =
        policies.keys.associateBy({it}, { policies[it]!!.actionValues(model)[it]!! })

}

