package Environment

import Agent.Agent

interface Environment {
    fun doAction(action: Action): Feedback
    fun getPosition(agent: Agent): Point
}