package agent.blocks

import agent.ActionMap
import environment.EnvironmentModel
import environment.Action
import java.util.*


class SelectionProvider {
    fun getGreedySelection() : (EnvironmentModel, (EnvironmentModel) -> ActionMap) -> Action.ActionType =
            {
                model, policyFunction ->
                policyFunction(model).actionMap.maxWith(Comparator<Map.Entry<Action.ActionType, Double>> {
                    pair1, pair2 -> if (pair1.value <= pair2.value) -1 else 1})?.key as Action.ActionType
            }
}
