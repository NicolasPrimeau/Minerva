package agent.blocks

import agent.policy.Policy
import environment.Action
import environment.EnvironmentModel
import environment.Objective
import java.util.*

class PolicySelectionProvider {

    fun getMaxPolicy() : (Map<Objective.ObjectiveType, Policy>, EnvironmentModel) -> Action.ActionType {
        return {policies, model ->
            val chosen = policies.keys.associateBy({ it }, { it -> policies[it]!!.act(model)  } )
            val values = policies.keys.associateBy({it}, {it -> policies[it]!!.actionValues(model)[it]!!
                    .actionMap[chosen[it]]!! * policies[it]!!.weight})
            chosen[chosen.keys.maxWith(Comparator<Objective.ObjectiveType> { x, y -> values[x]!!.compareTo(values[y]!!)})]!!
        }
    }


}

