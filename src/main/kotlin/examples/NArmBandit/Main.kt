package examples.NArmBandit

import agent.AgentConfiguration
import agent.blocks.ExplorationProvider
import agent.blocks.LearningProvider
import agent.blocks.SelectionProvider
import environment.Action
import environment.Point
import java.util.*

val LEARNING_RATE = 0.1
// The future discount is 0.0 here since it's a stationary environmental model
val FUTURE_DISCOUNT = 0.0
val EXPLORATION_PROBABILITY = 0.25
val EXPLORATION_DECREASE = 0.0001
val STOPPING_CRITERIA = 100000

fun main(args: Array<String>) {
    val env = ArmBanditEnvironment()


    val config = AgentConfiguration(
            ExplorationProvider().getMonotonicallyDecreasingStochasticExploration(EXPLORATION_PROBABILITY,
                    EXPLORATION_DECREASE),
            SelectionProvider().getGreedySelection(),
            LearningProvider().getQLearning(LEARNING_RATE, FUTURE_DISCOUNT),
            Point(1),
            Action(10))

    val agent = ArmBanditAgent(config, env)
    var x = 0
    println(agent.policy.actionValues(agent.environmentModel).actionMap.map { v ->  v.value})
    while (x < STOPPING_CRITERIA) {
        agent.behave()
        x += 1
    }
    println(agent.getTotalReward())
    println(agent.policy.actionValues(agent.environmentModel).actionMap.maxWith(
            Comparator<Map.Entry<Action.ActionType, Double>> {
                pair1, pair2 -> if (pair1.value <= pair2.value) -1 else 1})?.key as Action.ActionType)
    println(agent.policy.actionValues(agent.environmentModel).actionMap.map { v ->  v.value})
    println()
}
