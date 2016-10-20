package main.kotlin.Agent

import main.kotlin.Environment.Action
import java.security.SecureRandom

class ActionMap(actions: Array<Action>) {
    val actionMap: MutableMap<Action, Double> = mutableMapOf()
        get

    init {
        val random = SecureRandom()
        actions.forEach { action -> actionMap[action] = random.nextDouble() }
    }
}