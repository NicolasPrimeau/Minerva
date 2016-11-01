package examples.BasicGrid;

import agent.Agent;
import environment.Action;
import environment.Feedback;
import environment.Objective;
import environment.Point;
import environment.discrete.GridEnvironment;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

class BasicGridEnvironment extends GridEnvironment {

    private Point reward;

    BasicGridEnvironment(boolean random, Objective obj, double borderReward, @NotNull int... dimensions) {
        super(random, obj, borderReward, dimensions);
        Object[] rewards = this.getRewards();
        int[] maxPoint = new int[dimensions.length];

        for (int i=0; i<dimensions.length; i+=1) {
            maxPoint[i] = dimensions[dimensions.length-1]-1;
            if (i == dimensions.length-1) {
                rewards[dimensions[dimensions.length-1]-1] = 1.0;
            } else {
                rewards = (Object[])rewards[dimensions[dimensions.length-1]-1];
            }
        }
        reward = new Point(maxPoint);
    }

    @NotNull
    public Feedback doAction(@NotNull final Agent agent, @NotNull final Action.ActionType action) {
        Feedback fb = super.doAction(agent, action);
        if (fb.getNewPosition().equals(reward)) {
            int[] goBack = new int[this.getDimensions().length];
            Arrays.fill(goBack, 0);
            fb = new Feedback(fb.getRewards(),
                    new Point(goBack));
        }
        return fb;
    }
}
