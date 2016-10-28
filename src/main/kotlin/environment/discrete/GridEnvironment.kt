package environment.discrete

import environment.Environment
import environment.Feedback
import environment.Action
import agent.Agent
import environment.Point
import java.security.SecureRandom

open class GridEnvironment(random : Boolean=false, val borderReward: Double, vararg val dimensions: Int) : Environment {

    val rewards = {
        val r = SecureRandom()
        val defaultValue = if (random) { i: Int -> r.nextDouble() } else { i: Int -> 0.0 }
        if (dimensions.size == 1) {
            arrayCreation(0, defaultValue)
        } else if (dimensions.size > 1) {
            arrayCreation(0, 1, defaultValue)
        } else {
            throw IllegalArgumentException("Not valid dimension arguments")
        }
    } ()

    private fun arrayCreation(dim: Int, defaultValue: (Int) -> Double): Array<Double> {
        return Array(this.dimensions[dim], defaultValue)
    }

    private fun arrayCreation(dim: Int, dimplus: Int, defaultValue: (Int) -> Double): Array<out Any?> {
        return Array(dimensions[dim], {
            if (dimplus+1 >= dimensions.size ) {
                arrayCreation(dimplus, defaultValue)
            } else {
                arrayCreation(dimplus, dimplus+1, defaultValue)
            }
        })
    }

    override fun doAction(agent: Agent, action: Action.ActionType) : Feedback {
        if (agent.position.dimensions.size != this.dimensions.size) {
            throw IllegalArgumentException("Not in the same euclidean space")
        }
        val p = translatePosition(action, agent.position)
        return Feedback(if (p == agent.position) borderReward else this.getFeedback(0, p.dimensions, this.rewards), p)
    }

    fun getReward(vararg coordinates: Int): Double {
        if (coordinates.size > this.dimensions.size) {
            throw IllegalArgumentException("Not in the same euclidean space")
        }
        return this.getFeedback(0, coordinates, this.rewards)
    }

    private fun translatePosition(action: Action.ActionType, point: Point): Point {
        val dim = action.type/2
        val direction = action.type %2
        val coords = intArrayOf(*point.dimensions)

        if (dim > coords.size || dim > dimensions.size) {
            throw IllegalArgumentException("Invalid action for dimension")
        }

        coords[dim] += if (direction == 1) 1 else -1
        if (coords[dim] < 0 || coords[dim] >= dimensions[dim]) {
            return point
        } else {
            return Point(*coords)
        }

    }

    protected fun getFeedback(lvl: Int, actionDimensions: IntArray, grid: Array<out Any?>) : Double {
        // Kotlin's multi dimensional lists are close to elegant, but not yet there
        if (lvl < actionDimensions.size-1) {
            return getFeedback(lvl + 1, actionDimensions, grid[actionDimensions[lvl]] as Array<*>)
        } else {
            return grid[actionDimensions[lvl]] as Double
        }
    }

}