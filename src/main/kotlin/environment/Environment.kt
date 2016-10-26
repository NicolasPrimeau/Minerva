package environment

import agent.Agent

interface Environment {
    fun doAction(agent: Agent, action: Action.ActionType): Feedback
}