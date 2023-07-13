package macauyeah.personal.springbootdatajpa.entitytwo.database.specification;

import macauyeah.personal.springbootdatajpa.searchspecification.OperatorFilter;
import macauyeah.personal.springbootdatajpa.searchspecification.JoinSearchRequest;

public class Ref1Filter implements JoinSearchRequest {
    private OperatorFilter<Integer> columnOne;
    private String columnTwo;
    private String columnThree;

    public OperatorFilter<Integer> getColumnOne() {
        return columnOne;
    }

    public void setColumnOne(OperatorFilter<Integer> columnOne) {
        this.columnOne = columnOne;
    }

    public String getColumnTwo() {
        return columnTwo;
    }

    public void setColumnTwo(String columnTwo) {
        this.columnTwo = columnTwo;
    }

    public String getColumnThree() {
        return columnThree;
    }

    public void setColumnThree(String columnThree) {
        this.columnThree = columnThree;
    }

}
