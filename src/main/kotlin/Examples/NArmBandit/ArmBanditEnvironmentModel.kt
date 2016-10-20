package main.kotlin.Examples.NArmBandit

import main.kotlin.Agent.EnvironmentModel
import main.kotlin.Environment.Action
import java.security.SecureRandom
import java.util.*
import java.util.stream.IntStream

class ArmBanditEnvironmentModel : EnvironmentModel {

    override fun hashCode(): Int {
        return 100
    }

    override fun equals(other: Any?): Boolean {
        return other is ArmBanditEnvironmentModel
    }

}