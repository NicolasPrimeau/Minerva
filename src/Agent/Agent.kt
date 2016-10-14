package Agent

import Environment.Environment
import Environment.Action
import Environment.EnvironmentModel
import Environment.Feedback

abstract class Agent (val name: String, val env: Environment) {

    val policy : Policy = this.setupPolicy()

    init {
    }

    fun behave() {
        val action: Action = act()
        evaluateResponse(action, env.doAction(action))
        learn();
    }

    abstract fun setupPolicy() : Policy

    abstract fun act() : Action

    abstract fun evaluateResponse(action: Action, response: Feedback)

    abstract fun learn()

    abstract fun getEnvironmentModel(): EnvironmentModel

}