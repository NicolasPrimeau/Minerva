package Environment

import Agent.Agent

interface Environment {
    fun doAction(agent: Agent, action: Action): Feedback
}