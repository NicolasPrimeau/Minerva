package Agent

import Environment.Action
import Agent.EnvironmentModel


class Episode(val model: EnvironmentModel, val action: Action, val reward: Double) {

}
