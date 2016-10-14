package Agent

import Environment.Action
import Environment.EnvironmentModel


class Episode(val model: EnvironmentModel, val action: Action, val reward: Double) {

}
