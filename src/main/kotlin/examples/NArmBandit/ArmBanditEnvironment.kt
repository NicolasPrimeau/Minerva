package examples.NArmBandit

import environment.discrete.GridEnvironment
import environment.Feedback


class ArmBanditEnvironment : GridEnvironment(true, Feedback(0.0), 1, 10) {

}

