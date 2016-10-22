package Agent.blocks

import Agent.ActionMap
import Agent.EnvironmentModel
import Environment.Action
import java.util.*


class SelectionProvider() {
    fun getGreedySelection() : (EnvironmentModel, (EnvironmentModel) -> ActionMap) -> Action =
            {
                model, policyFunction ->
                policyFunction(model).actionMap.maxWith(Comparator<Map.Entry<Action, Double>> {
                    pair1, pair2 -> if (pair1.value <= pair2.value) -1 else 1})?.key as Action
            }
}
