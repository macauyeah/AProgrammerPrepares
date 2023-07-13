package macauyeah.personal.springbootdatajpa.searchspecification;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ForeignKeyInFilter {
    private List<BigInteger> in = new ArrayList<>();

    public List<BigInteger> getIn() {
        return in;
    }

    public void setIn(List<BigInteger> in) {
        this.in = in;
    }
    
}
