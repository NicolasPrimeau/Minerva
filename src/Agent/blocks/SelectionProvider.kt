package Agent.blocks

import Environment.Action


class SelectionProvider(private val action: Action) {

    fun getGreedySelection() : (MutableMap<Action, Double>) -> Action {
        return  {
            valueMap ->
            var minPair : MutableMap.MutableEntry<Action, Double> ? = null
            for (pair in valueMap) {
                if (minPair == null || pair.value < minPair.value) {
                    minPair = pair
                }
            }
            minPair?.key as Action
        }
    }

}
