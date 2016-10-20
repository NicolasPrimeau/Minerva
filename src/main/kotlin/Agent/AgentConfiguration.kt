package main.kotlin.Agent

import main.kotlin.Environment.Action

data class AgentConfiguration(val exploration : (Array<Action>) -> Action?,
                              val selection : (EnvironmentModel, (EnvironmentModel) -> ActionMap) -> Action,
                              val adaptFunction : (Double, Double, Double) -> Double) {
}