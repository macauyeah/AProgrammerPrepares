package macauyeah.personal.springbootdatajpa.entitytwo.database.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import macauyeah.personal.springbootdatajpa.entitytwo.database.entity.Ref2;

@Repository
public interface Ref2Repo extends JpaRepository<Ref2, BigInteger>, RevisionRepository<Ref2, BigInteger, Integer> {
}
