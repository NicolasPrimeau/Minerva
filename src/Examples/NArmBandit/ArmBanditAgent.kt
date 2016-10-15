package Examples.NArmBandit

import Agent.DiscretePolicy
import Agent.EnvironmentModel
import Agent.Policy
import Agent.SingleObjectiveAgent
import Agent.blocks.ExplorationProvider
import Agent.blocks.LearningProvider
import Agent.blocks.SelectionProvider
import Environment.Action

class ArmBanditAgent : SingleObjectiveAgent("Arm Bandit Agent", ArmBanditEnvironment()) {

    override fun setupPolicy(): Policy {
        return DiscretePolicy(
                ExplorationProvider().getStochasticExploration(0.1),
                SelectionProvider().getGreedySelection(),
                LearningProvider().getQLearning(0.1, 0.9),
                Action.values(), 1.0)
    }

    override fun setupEnvironmentModel(): EnvironmentModel {
        return ArmBanditEnvironmentModel()
    }

    override fun act(): Action {
        return this.policy.act(this.environmentModel)
    }

    override fun debug() {
        System.out.println(this.environmentModel)
    }
}