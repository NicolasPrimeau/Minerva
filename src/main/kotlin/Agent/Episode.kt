package main.kotlin.Agent

import main.kotlin.Environment.Action
import main.kotlin.Agent.EnvironmentModel

data class Episode(val model: EnvironmentModel, val action: Action, val reward: Double) {

}
