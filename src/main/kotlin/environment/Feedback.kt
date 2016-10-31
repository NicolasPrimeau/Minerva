package environment

data class Feedback(val rewards: Map<Objective.ObjectiveType, Double>, val newPosition: Point) {

}