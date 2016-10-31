package environment

import agent.Agent

abstract class Environment(val objectives: Objective) {
    abstract fun doAction(agent: Agent, action: Action.ActionType): Feedback
}