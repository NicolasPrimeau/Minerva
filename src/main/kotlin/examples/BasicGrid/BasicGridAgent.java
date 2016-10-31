package examples.BasicGrid;

import agent.AgentConfiguration;
import agent.policy.DiscretePolicy;
import agent.policy.Policy;
import agent.SingleObjectiveAgent;
import agent.policy.PolicyConfiguration;
import environment.Action;
import environment.Environment;
import environment.EnvironmentModel;
import org.jetbrains.annotations.NotNull;

class BasicGridAgent extends SingleObjectiveAgent {

    BasicGridAgent(@NotNull Environment env, @NotNull AgentConfiguration configuration) {
        super("Basic Grid Agent", env, configuration);
    }

    @NotNull
    @Override
    public Policy setupPolicy(@NotNull AgentConfiguration config) {
        return new DiscretePolicy(new PolicyConfiguration(
                config.getExploration(),
                config.getSelection(),
                config.getAdaptFunction(),
                config.getActions(),
                config.getObjectives().getType(0),
                1.0));
    }


    @NotNull
    @Override
    public EnvironmentModel setupEnvironmentModel(@NotNull AgentConfiguration config) {
        return new examples.BasicGrid.BasicGridEnvironmentModel(this::getPosition);
    }

    @NotNull
    @Override
    public Action.ActionType act() {
        return this.getPolicy().act(this.getEnvironmentModel());
    }
}
