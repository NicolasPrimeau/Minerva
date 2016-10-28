package agent

import environment.Environment
import environment.Action
import environment.Feedback
import environment.EnvironmentModel

abstract class Agent (val name: String, val env: Environment, config: AgentConfiguration) {

    val environmentModel = this.setupEnvironmentModel(config)
    val policy = this.setupPolicy(config)
    var position = config.location
        get

    fun behave() {
        val model = this.environmentModel.deepCopy();
        val action = act()
        val reward = env.doAction(this, action)
        evaluateResponse(model, action, reward)
        this.position = reward.newPosition
        learn()
    }

    abstract fun setupPolicy(config: AgentConfiguration) : Policy
    abstract fun setupEnvironmentModel(config: AgentConfiguration) : EnvironmentModel
    abstract fun act() : Action.ActionType
    abstract fun evaluateResponse(model: EnvironmentModel, action: Action.ActionType, response: Feedback)
    abstract fun learn()
    abstract fun getTotalReward(): Double
}