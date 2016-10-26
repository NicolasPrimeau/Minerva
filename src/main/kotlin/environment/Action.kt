package environment

class Action(val numActions: Int) {
    class ActionType(val type: Int) {
        override operator fun equals(other: Any?): Boolean {
            return other is Action.ActionType && other.type == this.type
        }

        override fun hashCode(): Int {
            return this.type
        }
    }

    fun getAllActionTypes(): Array<Action.ActionType> {
        return Array(numActions, { i -> Action.ActionType(i) })
    }

    fun getType(type: Int): Action.ActionType {
        if (type < 0 || type >= numActions) {
            throw IllegalArgumentException()
        }
        return Action.ActionType(type)
    }
}