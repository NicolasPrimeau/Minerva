package Agent

import Environment.Environment
import Environment.Action
import Environment.Feedback

abstract class Agent (val name: String, val env: Environment) {

    val environmentModel : EnvironmentModel = this.setupEnvironmentModel()
    val policy : Policy = this.setupPolicy()

    fun behave() {
        val action: Action = act()
        evaluateResponse(action, env.doAction(action))
        learn()
        debug()
    }

    abstract fun setupPolicy() : Policy
    abstract fun setupEnvironmentModel() : EnvironmentModel

    abstract fun act() : Action

    abstract fun evaluateResponse(action: Action, response: Feedback)

    abstract fun learn()

    open fun debug() {
        // Left Empty
    }

}