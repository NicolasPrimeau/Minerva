package agent

import environment.Environment
import environment.Action
import environment.Feedback
import environment.EnvironmentModel

abstract class Agent (val name: String, val env: Environment, config: AgentConfiguration) {

    val environmentModel = this.setupEnvironmentModel(config)
    val policy = this.setupPolicy(config)
    val position = config.location
        get

    fun behave() {
        val action = act()
        val reward = env.doAction(this, action)
        evaluateResponse(action, reward)
        if (reward.reward != 0.0) {
            learn()
        }
    }

    abstract fun setupPolicy(config: AgentConfiguration) : Policy
    abstract fun setupEnvironmentModel(config: AgentConfiguration) : EnvironmentModel
    abstract fun act() : Action.ActionType
    abstract fun evaluateResponse(action: Action.ActionType, response: Feedback)
    abstract fun learn()
    abstract fun getTotalReward(): Double
}