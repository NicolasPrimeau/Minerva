package agent

import environment.Environment
import environment.Action
import environment.Feedback

abstract class SingleObjectiveAgent(name: String, env: Environment, configuration: AgentConfiguration) :
        Agent(name, env, configuration) {

    val objective = ObjectiveEvaluator("singleObjective")
    var reward = 0.0

    override fun evaluateResponse(action: Action.ActionType, response: Feedback) {
        reward += response.reward
        this.objective.receiveEpisode(Episode(this.environmentModel, action, response.reward))
    }

    override fun learn() {
        this.policy.adapt(objective.getEpisodes())
        objective.clearEpisodes()
    }

    override fun getTotalReward() : Double {
        return reward
    }

}