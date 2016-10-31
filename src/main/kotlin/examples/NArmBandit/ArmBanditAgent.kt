package examples.NArmBandit

import agent.*
import agent.policy.DiscretePolicy
import agent.policy.Policy
import agent.policy.PolicyConfiguration
import environment.Action
import environment.EnvironmentModel

class ArmBanditAgent(config: AgentConfiguration, env: ArmBanditEnvironment) :
        SingleObjectiveAgent("Arm Bandit main.kotlin.Agent", env, config) {

    override fun setupPolicy(config: AgentConfiguration): Policy {
        return DiscretePolicy(PolicyConfiguration(
                config.exploration,
                config.selection,
                config.adaptFunction,
                config.actions,
                config.objectives.getType(0)
                ))
    }

    override fun setupEnvironmentModel(config: AgentConfiguration): EnvironmentModel {
        return ArmBanditEnvironmentModel()
    }

    override fun act(): Action.ActionType {
        return this.policy.act(this.environmentModel)
    }

}