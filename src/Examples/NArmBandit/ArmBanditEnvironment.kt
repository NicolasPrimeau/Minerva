package Examples.NArmBandit

import Environment.Environment
import Environment.Action
import Environment.Feedback
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

