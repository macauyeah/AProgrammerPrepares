package macauyeah.personal.springbootdatajpa.entityone.database.entity;

import java.math.BigInteger;

import jakarta.persistence.*;

@Entity
public class SomethingOne {
    // #region attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private Integer columnOne;
    private String columnTwo;
    @Version
    @Column(nullable = false)
    private long version = 0L;

    // #endregion attribute

    // #region getter setter

    public long getVersion() {
        return this.version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

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
    // #endregion getter setter
}
