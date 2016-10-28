package examples.NArmBandit

import agent.Agent
import environment.Action
import environment.discrete.GridEnvironment
import environment.Feedback


class ArmBanditEnvironment : GridEnvironment(true, 0.0, 10) {
    override fun doAction(agent: Agent, action: Action.ActionType) : Feedback {
        return Feedback(this.getFeedback(0, intArrayOf(action.type), this.rewards), agent.position)
    }
}

