package macauyeah.personal.springbootdatajpa.entitytwo.database.specification;

import macauyeah.personal.springbootdatajpa.searchspecification.ForeignKeyInFilter;

public class SomethingTwoFilter {
    private Integer columnOne;
    private String columnTwo;
    private String columnThree;
    private Ref1Filter ref1;
    private Ref2Filter ref2List;
    private ForeignKeyInFilter ref3;

    public Ref1Filter getRef1() {
        return ref1;
    }

    public void setRef1(Ref1Filter ref1) {
        this.ref1 = ref1;
    }

    public Ref2Filter getRef2List() {
        return ref2List;
    }

    public void setRef2List(Ref2Filter ref2List) {
        this.ref2List = ref2List;
    }

    public Integer getColumnOne() {
        return columnOne;
    }

    public void setColumnOne(Integer columnOne) {
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

    public ForeignKeyInFilter getRef3() {
        return ref3;
    }

    public void setRef3(ForeignKeyInFilter ref3) {
        this.ref3 = ref3;
    }

}
