package macauyeah.personal.springbootdatajpa.entitytwo.database.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import macauyeah.personal.springbootdatajpa.entitytwo.database.entity.Ref1;

@Repository
public interface Ref1Repo extends JpaRepository<Ref1, BigInteger>, RevisionRepository<Ref1, BigInteger, Integer> {
}
