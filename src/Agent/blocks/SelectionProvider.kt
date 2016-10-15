package Agent.blocks

import Environment.Action


class SelectionProvider() {

    fun getGreedySelection() : (Map<Action, Double>) -> Action {
        return  {
            valueMap ->
            var minPair : Map.Entry<Action, Double> ? = null
            for (pair in valueMap) {
                if (minPair == null || pair.value < minPair.value) {
                    minPair = pair
                }
            }
            minPair?.key as Action
        }
    }

}
