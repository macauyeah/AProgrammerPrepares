package macauyeah.personal.springbootdatajpa.entitytwo.database.entity;

import java.math.BigInteger;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity
@Audited
public class Ref3 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private Integer columnOne;
    private String columnTwo;
    private String columnThree;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

}
