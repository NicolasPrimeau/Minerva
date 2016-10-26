package agent

import environment.Action
import java.security.SecureRandom

class ActionMap(actions: Action) {
    val actionMap: MutableMap<Action.ActionType, Double> = mutableMapOf()
        get

    init {
        val random = SecureRandom()
        actions.getAllActionTypes().forEach { action -> actionMap[action] = random.nextDouble() }
    }
}