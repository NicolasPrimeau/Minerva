package examples.BasicGrid;

import environment.EnvironmentModel;
import environment.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

class BasicGridEnvironmentModel implements EnvironmentModel {

    private Supplier<Point> positionProvider;

    BasicGridEnvironmentModel(final Supplier<Point> provider) {
        this.positionProvider = provider;
    }

    private BasicGridEnvironmentModel(final Point p) {
        this.positionProvider = () -> p;
    }

    private Point getPoint() {
        return this.positionProvider.get();
    }

    @Override
    public int hashCode() {
        return positionProvider.get().hashCode();
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return other instanceof BasicGridEnvironmentModel &&
                ((BasicGridEnvironmentModel) other).getPoint().equals(this.positionProvider.get());
    }

    @NotNull
    public EnvironmentModel deepCopy() {
        return new BasicGridEnvironmentModel(this.positionProvider.get());
    }

    @Override
    public String toString() {
        return this.positionProvider.get().toString();
    }

}
