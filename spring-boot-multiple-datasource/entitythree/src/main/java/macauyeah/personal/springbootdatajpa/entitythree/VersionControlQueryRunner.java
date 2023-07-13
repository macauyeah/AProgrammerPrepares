package macauyeah.personal.springbootdatajpa.entitythree;

import java.util.Date;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import macauyeah.personal.springbootdatajpa.entitythree.database.entity.VersionControl;

@SpringBootApplication
public class VersionControlQueryRunner implements ApplicationRunner {
	private static Logger LOG = LoggerFactory.getLogger(VersionControlQueryRunner.class);
	@Autowired
	private EntityManagerFactory factory;

	public static void main(String[] args) {
		SpringApplication.run(VersionControlQueryRunner.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		LOG.info("running QueryRunner");
		this.tryVersionUpdate();
		LOG.info("finish QueryRunner");
	}

	public VersionControl tryVersionUpdate() {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		VersionControl vControl = new VersionControl();
		vControl.setRemark((new Date()).toString() + "0");
		em.persist(vControl);
		em.flush(); // version 0
		transaction.commit();

		transaction.begin();
		vControl.setRemark((new Date()).toString() + "1");
		vControl.setVersion(3); // no detch, no matter what you set, it won't trigger error
		em.flush(); // version 1
		transaction.commit();

		transaction.begin();
		em.detach(vControl);
		vControl.setRemark((new Date()).toString() + "2");
		vControl.setVersion(1); // must be 1
		em.merge(vControl);
		em.flush(); // version 2
		transaction.commit();

		transaction.begin();
		em.detach(vControl);
		vControl.setRemark((new Date()).toString() + "3");
		vControl.setVersion(10); // must be 2
		try {
			em.merge(vControl);
			em.flush(); // version 3
			transaction.commit();
		} catch (jakarta.persistence.OptimisticLockException e) {
			LOG.error(e.getMessage());
			transaction.rollback();
		}

		transaction.begin();
		em.detach(vControl);
		vControl.setRemark((new Date()).toString() + "3");
		vControl.setVersion(2); // must be 2
		try {
			em.merge(vControl);
			em.flush(); // version 3
			transaction.commit();
		} catch (jakarta.persistence.OptimisticLockException e) {
			LOG.error(e.getMessage());
			transaction.rollback();
		}
		return vControl;
	}
}
