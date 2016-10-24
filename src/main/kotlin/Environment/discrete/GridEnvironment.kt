package Environment.discrete

import Environment.Environment
import Environment.Feedback
import Environment.Point
import Environment.Action
import Agent.Agent
import java.util.*

class GridEnvironment(random : Boolean=false, vararg val dimensions: Int) : Environment {

    val rewards = {
        if (dimensions.size == 1) {
            recursiveArrayCreation(0)
        } else if (dimensions.size > 1) {
            recursiveArrayCreation(0, 1)
        } else {
            throw IllegalArgumentException("Not valid dimension arguments")
        }
    }

    private fun recursiveArrayCreation(dim: Int, random : Random? = null): Array<Feedback> {
        return Array(dim, { Feedback(if (random != null) random.nextDouble() else 0.0) })
    }

    private fun recursiveArrayCreation(dim: Int, dimplus: Int, random : Random? = null): Array<out Any?> {
        return Array(dimensions[dim], {
            if (dimplus+1 >= dimensions.size ) {
                recursiveArrayCreation(dimplus, random)
            } else {
                recursiveArrayCreation(dimplus, dimplus+1, random)
            }
        })
    }

    override fun doAction(action: Action) : Feedback {

    }

    override fun getPosition(agent: Agent) : Point {

    }

}