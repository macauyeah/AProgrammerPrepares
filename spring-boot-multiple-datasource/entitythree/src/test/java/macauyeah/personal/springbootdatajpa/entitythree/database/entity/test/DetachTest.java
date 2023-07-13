package macauyeah.personal.springbootdatajpa.entitythree.database.entity.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class DetachTest {

    private Logger LOG = LoggerFactory.getLogger(DetachTest.class);

    @Test
    public void test() {
    }
}