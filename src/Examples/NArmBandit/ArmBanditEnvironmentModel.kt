package Examples.NArmBandit

import Agent.EnvironmentModel
import Environment.Action
import java.security.SecureRandom
import java.util.*
import java.util.stream.IntStream

class ArmBanditEnvironmentModel : EnvironmentModel {

    val env: MutableList<Double> = ArrayList(Action.values().size)

    init {
        val r : Random = SecureRandom()
        IntStream.range(0, Action.values().size).forEach { env.add(r.nextGaussian()) }
    }

    override fun hashCode(): Int {
        return 100
    }

    override fun equals(other: Any?): Boolean {
        return other is ArmBanditEnvironment
    }
}