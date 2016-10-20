package main.kotlin.Environment

interface Environment {
    fun doAction(action: Action): Feedback
}