package Agent

import Environment.Environment
import Environment.Action
import Environment.Feedback

abstract class SingleObjectiveAgent(name: String, env: Environment) : Agent(name, env) {

    val objective : ObjectiveEvaluator = ObjectiveEvaluator("singleObjective")

    override fun evaluateResponse(action: Action, response: Feedback) {
        this.objective.receiveEpisode(Episode(this.environmentModel, action, response.reward))
    }

    override fun learn() {
        this.policy.adapt(objective.getEpisodes())
    }
}