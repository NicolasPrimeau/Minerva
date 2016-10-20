package main.kotlin.Examples.NArmBandit

import main.kotlin.Environment.Environment
import main.kotlin.Environment.Action
import main.kotlin.Environment.Feedback
import java.security.SecureRandom
import java.util.*

class ArmBanditEnvironment : Environment {

    val rewards: MutableList<Double> = mutableListOf()

    init {
        val r = SecureRandom()
        Action.values().forEach { rewards.add(r.nextDouble() * 100) }
    }

    override fun doAction(action: Action): Feedback {
        val r: Random = SecureRandom()
        val mean: Double = rewards[Action.values().indexOf(action)]
        return Feedback(mean + r.nextGaussian() * mean)
    }
}

