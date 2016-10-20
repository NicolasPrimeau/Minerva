package main.kotlin.Examples.NArmBandit

import main.kotlin.Environment.Environment
import main.kotlin.Environment.Action
import main.kotlin.Environment.Feedback
import java.security.SecureRandom
import java.util.*

class ArmBanditEnvironment : Environment {

    val rewards: List<Double> = listOf(0.1, -0.2, 0.2, -0.3, 0.3, -0.4, 0.4, -0.5, 0.5)

    override fun doAction(action: Action): Feedback {
        val r: Random = SecureRandom()
        val mean: Double = rewards[Action.values().indexOf(action)]
        return Feedback(mean + r.nextGaussian() * mean)
    }
}

