package macauyeah.personal.springbootdatajpa.entityone.database.entity.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import macauyeah.personal.springbootdatajpa.entityone.database.entity.SomethingOne;
import macauyeah.personal.springbootdatajpa.entityone.database.repository.SomethingOneRepo;

@ActiveProfiles("test")
@SpringBootTest
public class EntityOneConnectionTransactionActiveTest {
    @Autowired
    private SomethingOneRepo oneRepo;
    private Logger LOG = LoggerFactory.getLogger(EntityOneConnectionTransactionActiveTest.class);

    @Test
    @Transactional
    public void test() {
        assertNotNull(oneRepo);
        if (oneRepo.count() > 1) {
            oneRepo.findAll();
        }
        SomethingOne one = new SomethingOne();
        one.setColumnOne(1);
        one.setColumnTwo("columnTwo");
        oneRepo.save(one);

        one.setColumnOne(2);
        oneRepo.save(one);

        assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
        if (TransactionSynchronizationManager.isActualTransactionActive()){
            LOG.info("no ObjectOptimisticLockingFailureException");
            one.setVersion(0);
            oneRepo.save(one); // if you mark the test as transactional, no exception will be flow
        } else {
            assertTrue(false);
        }

        oneRepo.findAllByColumnTwoContains("Tw");
    }
}