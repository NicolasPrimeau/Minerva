package main.kotlin.Agent

import main.kotlin.Environment.Action
import main.kotlin.Agent.EnvironmentModel

abstract class Policy(val weight: Double) {
    abstract fun act(model: EnvironmentModel) : Action
    abstract fun adapt(episodes: List<Episode>)
    abstract fun actionValues(model: EnvironmentModel) : Map<Action, Double>?
}