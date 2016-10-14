package Agent.blocks

import Environment.Action

class LearningProvider(private val action: Action) {

    fun getQLearning(alpha: Double, gamma: Double) : (Double, Double, Double) -> Double {
        return {current, reward, future ->  current + alpha * (reward + gamma * (future) - current)}
    }

}