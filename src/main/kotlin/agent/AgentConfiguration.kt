package agent

import environment.Action
import environment.EnvironmentModel
import environment.Point

open class AgentConfiguration(val exploration : (Action) -> Action.ActionType?,
                              val selection : (EnvironmentModel, (EnvironmentModel) -> ActionMap) -> Action.ActionType,
                              val adaptFunction : (Double, Double, Double) -> Double,
                              val location: Point,
                              val actions: Action) {
}