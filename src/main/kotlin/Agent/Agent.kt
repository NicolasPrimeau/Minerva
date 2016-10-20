package main.kotlin.Agent

import main.kotlin.Environment.Environment
import main.kotlin.Environment.Action
import main.kotlin.Environment.Feedback

abstract class Agent (val name: String, val env: Environment) {

    val environmentModel : EnvironmentModel = this.setupEnvironmentModel()
    val policy : Policy = this.setupPolicy()

    fun behave() {
        val action: Action = act()
        evaluateResponse(action, env.doAction(action))
        learn()
    }

    abstract fun setupPolicy() : Policy
    abstract fun setupEnvironmentModel() : EnvironmentModel
    abstract fun act() : Action
    abstract fun evaluateResponse(action: Action, response: Feedback)
    abstract fun learn()
    abstract fun getTotalReward(): Double
}