package Examples.NArmBandit

import Agent.AgentConfiguration
import Agent.blocks.ExplorationProvider
import Agent.blocks.LearningProvider
import Agent.blocks.SelectionProvider
import Environment.Action
import java.util.*

val LEARNING_RATE = 0.1
// The future discount is 0.0 here since it's a stationary environmental model
val FUTURE_DISCOUNT = 0.0
val EXPLORATION_PROBABILITY = 0.25
val EXPLORATION_DECREASE = 0.0001
val STOPPING_CRITERIA = 100000

fun main(args: Array<String>) {
    val env = ArmBanditEnvironment()
    println(env.rewards.indexOf(env.rewards.max()))
    println(Action.values()[env.rewards.indexOf(env.rewards.max())])
    println(env.rewards)
    println()

    val config = AgentConfiguration(
            ExplorationProvider().getMonotonicallyDecreasingStochasticExploration(EXPLORATION_PROBABILITY,
                    EXPLORATION_DECREASE),
            SelectionProvider().getGreedySelection(),
            LearningProvider().getQLearning(LEARNING_RATE, FUTURE_DISCOUNT))

    val agent = ArmBanditAgent(config, env)
    var x = 0
    println(agent.policy.actionValues(agent.environmentModel).actionMap.map { v ->  v.value})
    while (x < STOPPING_CRITERIA) {
        agent.behave()
        x += 1
    }
    println(agent.getTotalReward())
    println(agent.policy.actionValues(agent.environmentModel).actionMap.maxWith(
            Comparator<Map.Entry<Action, Double>> {
                pair1, pair2 -> if (pair1.value <= pair2.value) -1 else 1})?.key as Action)
    println(agent.policy.actionValues(agent.environmentModel).actionMap.map { v ->  v.value})
    println()
}
