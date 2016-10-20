package main.kotlin.Examples.NArmBandit

import main.kotlin.Agent.DiscretePolicy
import main.kotlin.Agent.EnvironmentModel
import main.kotlin.Agent.Policy
import main.kotlin.Agent.SingleObjectiveAgent
import main.kotlin.Agent.blocks.ExplorationProvider
import main.kotlin.Agent.blocks.LearningProvider
import main.kotlin.Agent.blocks.SelectionProvider
import main.kotlin.Environment.Action

class ArmBanditAgent(env: ArmBanditEnvironment) : SingleObjectiveAgent("Arm Bandit main.kotlin.Agent", env) {

    override fun setupPolicy(): Policy {
        return DiscretePolicy(
                ExplorationProvider().getMonotonicallyDecreasingExploration(0.25, 0.001),
                SelectionProvider().getGreedySelection(),
                LearningProvider().getQLearning(0.1, 0.0),
                Action.values(), 1.0)
    }

    override fun setupEnvironmentModel(): EnvironmentModel {
        return ArmBanditEnvironmentModel()
    }

    override fun act(): Action {
        return this.policy.act(this.environmentModel)
    }

}