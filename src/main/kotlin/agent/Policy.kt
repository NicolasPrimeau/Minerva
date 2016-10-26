package agent

import environment.Action
import environment.EnvironmentModel


abstract class Policy(val weight: Double=1.0) {
    abstract fun act(model: EnvironmentModel) : Action.ActionType
    abstract fun adapt(episodes: List<Episode>)
    abstract fun actionValues(model: EnvironmentModel) : ActionMap
}