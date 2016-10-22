package Agent

import Environment.Action

data class Episode(val model: EnvironmentModel, val action: Action, val reward: Double) {

}
