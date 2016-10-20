package main.kotlin.Examples.NArmBandit

import main.kotlin.Agent.*
import main.kotlin.Agent.blocks.ExplorationProvider
import main.kotlin.Agent.blocks.LearningProvider
import main.kotlin.Agent.blocks.SelectionProvider
import main.kotlin.Environment.Action

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