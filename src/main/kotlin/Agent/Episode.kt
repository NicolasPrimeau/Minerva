package Agent

import Environment.Action
import Environment.EnvironmentModel

data class Episode(val model: EnvironmentModel, val action: Action, val reward: Double) {

}
