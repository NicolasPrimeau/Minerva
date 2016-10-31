package agent.policy

import agent.ActionMap
import environment.Action
import environment.EnvironmentModel
import environment.Objective

open class PolicyConfiguration(val exploration : (Action) -> Action.ActionType?,
                               val selection : (EnvironmentModel, (EnvironmentModel) -> ActionMap) -> Action.ActionType,
                               val adaptFunction : (Double, Double, Double) -> Double,
                               val actions : Action,
                               val target: Objective.ObjectiveType,
                               var weight: Double=1.0) {

}

