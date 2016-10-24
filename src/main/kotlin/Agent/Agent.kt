package Agent

import Environment.Environment
import Environment.Action
import Environment.Feedback

abstract class Agent (val name: String, val env: Environment, config: AgentConfiguration) {

    val environmentModel = this.setupEnvironmentModel(config)
    val policy = this.setupPolicy(config)

    fun behave() {
        val action = act()
        val reward = env.doAction(action)
        evaluateResponse(action, reward)
        if (reward.reward != 0.0) {
            learn()
        }
    }

    abstract fun setupPolicy(config: AgentConfiguration) : Policy
    abstract fun setupEnvironmentModel(config: AgentConfiguration) : Environment.EnvironmentModel
    abstract fun act() : Action
    abstract fun evaluateResponse(action: Action, response: Feedback)
    abstract fun learn()
    abstract fun getTotalReward(): Double
}