package Environment.discrete

import Environment.Environment
import Environment.Feedback
import Environment.Action
import Agent.Agent
import java.security.SecureRandom

class GridEnvironment(random : Boolean=false, val borderReward: Feedback, vararg val dimensions: Int) : Environment {

    val rewards = {
        val r = SecureRandom()
        val defaultValue = if (random) { i: Int -> Feedback(r.nextDouble()) } else { i: Int -> Feedback(0.0) }
        if (dimensions.size == 1) {
            arrayCreation(0, defaultValue)
        } else if (dimensions.size > 1) {
            arrayCreation(0, 1, defaultValue)
        } else {
            throw IllegalArgumentException("Not valid dimension arguments")
        }
    } ()

    private fun arrayCreation(dim: Int, defaultValue: (Int) -> Feedback): Array<Feedback> {
        return Array(dim, defaultValue)
    }

    private fun arrayCreation(dim: Int, dimplus: Int, defaultValue: (Int) -> Feedback): Array<out Any?> {
        return Array(dimensions[dim], {
            if (dimplus+1 >= dimensions.size ) {
                arrayCreation(dimplus, defaultValue)
            } else {
                arrayCreation(dimplus, dimplus+1, defaultValue)
            }
        })
    }

    override fun doAction(agent: Agent, action: Action) : Feedback {
        if (agent.position.dimensions.size != this.dimensions.size) {
            throw IllegalArgumentException("Not in the same euclidean space")
        }
        return this.getFeedback(0, agent.position.dimensions, this.rewards)
    }

    private fun getFeedback(lvl: Int, dimensions: IntArray, grid: Array<out Any?>) : Feedback {
        // Kotlin's multi dimensional lists are close to elegant, but not yet there
        if (lvl < dimensions.size && dimensions[lvl] < grid.size) {
            return borderReward
        } else if (lvl < dimensions.size) {
            return getFeedback(lvl + 1, dimensions, grid[dimensions[lvl]] as Array<Any?>)
        } else {
            return grid[dimensions[lvl]] as Feedback
        }
    }

}