package agent

import environment.Action
import environment.EnvironmentModel

data class Episode(val model: EnvironmentModel, val action: Action.ActionType, val reward: Double) {

}
