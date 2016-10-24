package Agent

import Environment.Action
import Environment.EnvironmentModel
import Environment.Point

data class AgentConfiguration(val exploration : (Array<Action>) -> Action?,
                              val selection : (EnvironmentModel, (EnvironmentModel) -> ActionMap) -> Action,
                              val adaptFunction : (Double, Double, Double) -> Double,
                              val location: Point) {
}