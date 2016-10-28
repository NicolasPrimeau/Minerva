package main.kotlin.examples.BasicGrid;

import agent.AgentConfiguration;
import agent.DiscretePolicy;
import agent.Policy;
import agent.SingleObjectiveAgent;
import environment.Action;
import environment.Environment;
import environment.EnvironmentModel;
import org.jetbrains.annotations.NotNull;

public class BasicGridAgent extends SingleObjectiveAgent {

    public BasicGridAgent(@NotNull Environment env, @NotNull AgentConfiguration configuration) {
        super("Basic Grid Agent", env, configuration);
    }

    @NotNull
    @Override
    public Policy setupPolicy(@NotNull AgentConfiguration config) {
        return new DiscretePolicy(
                config.getExploration(),
                config.getSelection(),
                config.getAdaptFunction(),
                config.getActions(),
                1.0
        );
    }

    @NotNull
    @Override
    public EnvironmentModel setupEnvironmentModel(@NotNull AgentConfiguration config) {
        return new BasicGridEnvironmentModel(this::getPosition);
    }

    @NotNull
    @Override
    public Action.ActionType act() {
        return this.getPolicy().act(this.getEnvironmentModel());
    }
}
