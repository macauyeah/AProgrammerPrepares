package macauyeah.personal.springbootdatajpa.searchspecification;

public class BetweenFilter<X extends Comparable<? super X>> {
    private X lowerBound;
    private X upperBound;

    public BetweenFilter(X lower, X upper){
        this.lowerBound = lower;
        this.upperBound = upper;
    }

    public X getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(X lowerBound) {
        this.lowerBound = lowerBound;
    }

    public X getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(X upperBound) {
        this.upperBound = upperBound;
    }
}
