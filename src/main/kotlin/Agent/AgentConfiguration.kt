package Agent

import Environment.Action
import Environment.EnvironmentModel

data class AgentConfiguration(val exploration : (Array<Action>) -> Action?,
                              val selection : (EnvironmentModel, (EnvironmentModel) -> ActionMap) -> Action,
                              val adaptFunction : (Double, Double, Double) -> Double) {
}