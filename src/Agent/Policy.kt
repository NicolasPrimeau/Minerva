package Agent

import Environment.Action
import Environment.EnvironmentModel

abstract class Policy(val weight: Double) {
    abstract fun act(model: EnvironmentModel) : Action
    abstract fun adapt(episodes: List<Episode>)
}