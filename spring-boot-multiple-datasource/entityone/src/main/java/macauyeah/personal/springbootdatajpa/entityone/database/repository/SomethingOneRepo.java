package macauyeah.personal.springbootdatajpa.entityone.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import macauyeah.personal.springbootdatajpa.entityone.database.entity.SomethingOne;

import java.util.Collection;
import java.util.List;
import java.math.BigInteger;

@Repository
public interface SomethingOneRepo extends JpaRepository<SomethingOne, BigInteger>, JpaSpecificationExecutor<SomethingOne> {
    List<SomethingOne> findAllByColumnOneIn(Collection<Integer> columnOne);
    List<SomethingOne> findAllByColumnTwoContains(String columnTwo);
}
