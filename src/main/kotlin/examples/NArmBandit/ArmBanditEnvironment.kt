package examples.NArmBandit

import agent.Agent
import environment.Action
import environment.discrete.GridEnvironment
import environment.Feedback
import environment.Objective


class ArmBanditEnvironment : GridEnvironment(true, Objective(1), 0.0, 10) {
    override fun doAction(agent: Agent, action: Action.ActionType) : Feedback {
        return Feedback(
                mapOf(Pair(objectives.getType(0), this.getFeedback(0, intArrayOf(action.type), this.rewards))),
                agent.position)
    }
}

