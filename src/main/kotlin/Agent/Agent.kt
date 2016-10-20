package main.kotlin.Agent

import main.kotlin.Environment.Environment
import main.kotlin.Environment.Action
import main.kotlin.Environment.Feedback

abstract class Agent (val name: String, val env: Environment, config: AgentConfiguration) {

    val environmentModel = this.setupEnvironmentModel(config)
    val policy = this.setupPolicy(config)

    fun behave() {
        val action = act()
        evaluateResponse(action, env.doAction(action))
        learn()
    }

    abstract fun setupPolicy(config: AgentConfiguration) : Policy
    abstract fun setupEnvironmentModel(config: AgentConfiguration) : EnvironmentModel
    abstract fun act() : Action
    abstract fun evaluateResponse(action: Action, response: Feedback)
    abstract fun learn()
    abstract fun getTotalReward(): Double
}