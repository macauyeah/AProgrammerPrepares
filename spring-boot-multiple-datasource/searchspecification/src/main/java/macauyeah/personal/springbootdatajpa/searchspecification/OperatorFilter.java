package macauyeah.personal.springbootdatajpa.searchspecification;

import java.util.List;

public class OperatorFilter<X extends Comparable<? super X>> {
    private Boolean isNull;
    private BetweenFilter<X> between;
    private List<X> in;
    private X equalTo;
    private X lessThanOrEqualTo;
    private X greaterThanOrEqualTo;
    private String like;

    public String toString() {
        return "to be implement for debugging";
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public Boolean getIsNull() {
        return isNull;
    }

    public void setIsNull(Boolean isNull) {
        this.isNull = isNull;
    }

    public BetweenFilter<X> getBetween() {
        return between;
    }

    public void setBetween(BetweenFilter<X> between) {
        this.between = between;
    }

    public List<X> getIn() {
        return in;
    }

    public void setIn(List<X> in) {
        this.in = in;
    }

    public X getEqualTo() {
        return equalTo;
    }

    public void setEqualTo(X equalTo) {
        this.equalTo = equalTo;
    }

    public X getLessThanOrEqualTo() {
        return lessThanOrEqualTo;
    }

    public void setLessThanOrEqualTo(X lessThanOrEqualTo) {
        this.lessThanOrEqualTo = lessThanOrEqualTo;
    }

    public X getGreaterThanOrEqualTo() {
        return greaterThanOrEqualTo;
    }

    public void setGreaterThanOrEqualTo(X greaterThanOrEqualTo) {
        this.greaterThanOrEqualTo = greaterThanOrEqualTo;
    }

}
