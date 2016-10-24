package Examples.NArmBandit

import Environment.Environment
import Environment.Action
import Environment.Feedback
import java.security.SecureRandom
import java.util.*

class ArmBanditEnvironment : Environment() {

    init {
        val r = SecureRandom()
        Action.values().forEach { rewards.add(r.nextDouble() * 100) }
    }

    override fun doAction(action: Action): Feedback {
        val r: Random = SecureRandom()
        val mean: Double = rewards[Action.values().indexOf(action)]
        return Feedback(mean + r.nextGaussian() * mean/4)
    }
}

