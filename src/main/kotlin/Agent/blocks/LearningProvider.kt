package main.kotlin.Agent.blocks


class LearningProvider() {

    fun getQLearning(alpha: Double, gamma: Double) : (Double, Double, Double) -> Double {
        return {reward, current, future ->  current + alpha * (reward + gamma * (future) - current)}
    }

}