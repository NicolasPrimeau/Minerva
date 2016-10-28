package examples.NArmBandit

import environment.EnvironmentModel

class ArmBanditEnvironmentModel : EnvironmentModel {

    override fun deepCopy(): EnvironmentModel {
        return ArmBanditEnvironmentModel()
    }

    override fun hashCode(): Int {
        // Make sure it all hashes at the same place
        return 0
    }

    override fun equals(other: Any?): Boolean {
        // All Environmental Models are equal for this problem
        return other is ArmBanditEnvironmentModel
    }

}