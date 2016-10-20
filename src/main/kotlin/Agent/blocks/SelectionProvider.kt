package main.kotlin.Agent.blocks

import main.kotlin.Environment.Action


class SelectionProvider() {

    fun getGreedySelection() : (Map<Action, Double>) -> Action {
        return  {
            valueMap ->
            var maxPair : Map.Entry<Action, Double> ? = null
            for (pair in valueMap) {
                if (maxPair == null || pair.value > maxPair.value) {
                    maxPair = pair
                }
            }
            maxPair?.key as Action
        }
    }

}
