package main.kotlin.Agent

import main.kotlin.Environment.Environment
import main.kotlin.Environment.Action
import main.kotlin.Environment.Feedback

abstract class SingleObjectiveAgent(name: String, env: Environment, configuration: AgentConfiguration) :
        Agent(name, env, configuration) {

    val objective = ObjectiveEvaluator("singleObjective")
    var reward = 0.0

    override fun evaluateResponse(action: Action, response: Feedback) {
        reward += response.reward
        this.objective.receiveEpisode(Episode(this.environmentModel, action, response.reward))
    }

    override fun learn() {
        this.policy.adapt(objective.getEpisodes())
    }

    override fun getTotalReward() : Double {
        return reward
    }

}