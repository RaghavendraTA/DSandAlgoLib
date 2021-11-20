package utilities;

/*
 * created by raghavendra.ta on 02-Nov-2021
 */

public class UnDirectedGraph<T> extends DirectedGraph<T> {

    @Override
    public void addLine(T u, T v) {
        this.addLine(u, v, 1L);
    }

    @Override
    public void addLine(T u, T v, long cost) {
        super.addLine(u, v, cost);
        super.addLine(v, u, cost);
    }
}
