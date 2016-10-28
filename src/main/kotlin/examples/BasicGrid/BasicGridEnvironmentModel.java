package main.kotlin.examples.BasicGrid;

import environment.EnvironmentModel;
import environment.Point;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BasicGridEnvironmentModel implements EnvironmentModel {

    private Supplier<Point> positionProvider;

    public BasicGridEnvironmentModel(final Supplier<Point> provider) {
        this.positionProvider = provider;
    }

    public BasicGridEnvironmentModel(final Point p) {
        this.positionProvider = () -> p;
    }

    public Point getPoint() {
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

    public EnvironmentModel deepCopy() {
        return new BasicGridEnvironmentModel(this.positionProvider.get());
    }

    @Override
    public String toString() {
        return this.positionProvider.get().toString();
    }

}
