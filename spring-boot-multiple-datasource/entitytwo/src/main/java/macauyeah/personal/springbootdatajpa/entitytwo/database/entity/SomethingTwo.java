package macauyeah.personal.springbootdatajpa.entitytwo.database.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class SomethingTwo {
    // #region attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private Integer columnOne;
    private String columnTwo;
    private String columnThree;
    @ManyToOne
    private Ref1 ref1;
    @OneToMany
    private List<Ref2> ref2List = new ArrayList<>();
    @ManyToOne
    private Ref3 ref3;
    // #endregion attribute

    public void addRef2(Ref2 ref2) {
        this.getRef2List().add(ref2);
    }

    public void removeRef2(Ref2 ref2) {
        this.getRef2List().remove(ref2);
    }

    // #region getter setter
    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getColumnOne() {
        return this.columnOne;
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

    public Ref1 getRef1() {
        return ref1;
    }

    public void setRef1(Ref1 ref1) {
        this.ref1 = ref1;
    }

    public List<Ref2> getRef2List() {
        return ref2List;
    }

    public void setRef2List(List<Ref2> ref2List) {
        this.ref2List = ref2List;
    }

    public Ref3 getRef3() {
        return ref3;
    }

    public void setRef3(Ref3 ref3) {
        this.ref3 = ref3;
    }
    // #endregion getter setter
}
