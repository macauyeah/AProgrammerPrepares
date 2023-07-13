package macauyeah.personal.springbootdatajpa.entitytwo.database.entity.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import macauyeah.personal.springbootdatajpa.entitytwo.database.repository.SomethingTwoRepo;

@ActiveProfiles("test")
@SpringBootTest
public class EntityTwoConnectionTest {
    @Autowired
    private SomethingTwoRepo twoRepo;

    @Test
    public void test() {
        assertNotNull(twoRepo);
        if (twoRepo.count() > 1) {
            twoRepo.findAll();
        }

    }
}