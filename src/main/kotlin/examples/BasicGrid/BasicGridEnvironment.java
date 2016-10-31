package examples.BasicGrid;

import agent.Agent;
import environment.Action;
import environment.Feedback;
import environment.Objective;
import environment.Point;
import environment.discrete.GridEnvironment;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

class BasicGridEnvironment extends GridEnvironment {

    private Point max;

    BasicGridEnvironment(boolean random, Objective obj, double borderReward, @NotNull int... dimensions) {
        super(random, obj, borderReward, dimensions);
        Double maxVal = null;
        for (int i=0; i<dimensions[0]; i+=1) {
            for (int j=0; j<dimensions[1]; j+=1) {
                double reward = this.getReward(i, j);
                if (maxVal == null || reward > maxVal) {
                    maxVal = reward;
                    this.max = new Point(i, j);
                }
            }
        }
    }

    @NotNull
    public Feedback doAction(@NotNull final Agent agent, @NotNull final Action.ActionType action) {
        Feedback fb = super.doAction(agent, action);
        if (fb.getNewPosition().equals(max)) {
            Random r = new Random();

            fb = new Feedback(fb.getRewards(),
                    new Point(r.nextInt(this.getDimensions()[0]), r.nextInt(this.getDimensions()[1])));
        }
        return fb;
    }
}
