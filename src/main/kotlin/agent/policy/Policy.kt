package agent.policy

import agent.ActionMap
import agent.ObjectiveEvaluator
import environment.Action
import environment.EnvironmentModel
import environment.Objective


abstract class Policy(var weight: Double=1.0) {
    abstract fun act(model: EnvironmentModel) : Action.ActionType
    abstract fun adapt(objectives: Map<Objective.ObjectiveType, ObjectiveEvaluator>)
    abstract fun actionValues(model: EnvironmentModel) : Map<Objective.ObjectiveType, ActionMap>
}