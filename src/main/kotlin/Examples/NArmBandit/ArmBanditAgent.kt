package Examples.NArmBandit

import Agent.*
import Environment.Action
import Environment.EnvironmentModel

class ArmBanditAgent(config: AgentConfiguration, env: ArmBanditEnvironment) :
        SingleObjectiveAgent("Arm Bandit main.kotlin.Agent", env, config) {

    override fun setupPolicy(config: AgentConfiguration): Policy {
        return DiscretePolicy(
                config.exploration,
                config.selection,
                config.adaptFunction,
                Action.values())
    }

    override fun setupEnvironmentModel(config: AgentConfiguration): EnvironmentModel {
        return ArmBanditEnvironmentModel()
    }

    override fun act(): Action {
        return this.policy.act(this.environmentModel)
    }

}