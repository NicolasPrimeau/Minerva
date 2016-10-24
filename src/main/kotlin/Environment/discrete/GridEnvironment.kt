package Environment.discrete

import Environment.Environment

abstract class GridEnvironment(random : Boolean=false, vararg val dimensions: Double) : Environment {
    
    init{
        if (random) {

        }
    }

}