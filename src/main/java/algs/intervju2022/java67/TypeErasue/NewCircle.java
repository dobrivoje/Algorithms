package algs.intervju2022.java67.TypeErasue;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class NewCircle<T> implements NewOvalShape<T> {
private final T radius;

    public NewCircle(T radius) {
        this.radius = radius;
    }

    @Override
    public double getRadius() {
        return Double.parseDouble( "12" );
    }

    @Override
    public String getName() {
        return "new circle";
    }

    @Override
    public T getType() {
        return null;
    }
}
