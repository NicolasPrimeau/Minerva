package main.kotlin.Examples.NArmBandit

import main.kotlin.Agent.Agent

fun main(args: Array<String>) {
    val env : ArmBanditEnvironment = ArmBanditEnvironment()
    println(env.rewards)
    println()
    val agent : Agent = ArmBanditAgent(env)
    var x: Int = 0
    while (x < 1000) {
        agent.behave()
        println(agent.getTotalReward())
        println(agent.policy.actionValues(agent.environmentModel)!!.map { v ->  v.value})
        println()
        x += 1
    }
}
