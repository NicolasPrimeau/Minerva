package Environment

interface Environment {
    fun doAction(action: Action): Feedback
}