package environment

class Objective(val numObjectives: Int) {
    class ObjectiveType(val type: Int) {
        override operator fun equals(other: Any?): Boolean {
            return other is Objective.ObjectiveType && other.type == this.type
        }

        override fun hashCode(): Int {
            return this.type
        }
    }

    fun getAllObjectiveTypes(): Array<Objective.ObjectiveType> {
        return Array(numObjectives, { i -> Objective.ObjectiveType(i) })
    }

    fun getType(type: Int): Objective.ObjectiveType {
        if (type < 0 || type >= numObjectives) {
            throw IllegalArgumentException()
        }
        return Objective.ObjectiveType(type)
    }
}