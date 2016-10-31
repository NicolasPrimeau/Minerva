package agent


import environment.Objective
import java.util.*

class ObjectiveEvaluator(val name: String, val target: Objective.ObjectiveType) {

    private var episodes : MutableList<Episode> = LinkedList()

    fun receiveEpisode(episode: Episode) {
        episodes.add(episode);
    }

    fun getEpisodes(): List<Episode> {
        return LinkedList(episodes)
    }

    fun clearEpisodes() {
        this.episodes.clear()
    }
}