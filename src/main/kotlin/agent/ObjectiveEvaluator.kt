package agent


import java.util.*

class ObjectiveEvaluator(val name: String) {

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