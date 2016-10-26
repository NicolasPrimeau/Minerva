package examples.NArmBandit

import agent.*
import environment.Action
import environment.EnvironmentModel
import environment.Point

class ArmBanditAgent(config: AgentConfiguration, env: ArmBanditEnvironment) :
        SingleObjectiveAgent("Arm Bandit main.kotlin.Agent", env, config) {

    override fun setupPolicy(config: AgentConfiguration): Policy {
        return DiscretePolicy(
                config.exploration,
                config.selection,
                config.adaptFunction,
                config.actions
                )
    }

    override fun setupEnvironmentModel(config: AgentConfiguration): EnvironmentModel {
        return ArmBanditEnvironmentModel()
    }

    override fun act(): Action.ActionType {
        return this.policy.act(this.environmentModel)
    }

}