package macauyeah.personal.springbootdatajpa.applicationRunner.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

// import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import macauyeah.personal.springbootdatajpa.entityone.database.repository.SomethingOneRepo;
import macauyeah.personal.springbootdatajpa.entitytwo.database.entity.*;
import macauyeah.personal.springbootdatajpa.entitytwo.database.repository.*;
import macauyeah.personal.springbootdatajpa.searchspecification.BetweenFilter;
import macauyeah.personal.springbootdatajpa.searchspecification.ForeignKeyInFilter;
import macauyeah.personal.springbootdatajpa.searchspecification.OperatorFilter;
import macauyeah.personal.springbootdatajpa.entitytwo.database.specification.Ref1Filter;
import macauyeah.personal.springbootdatajpa.entitytwo.database.specification.Ref2Filter;
import macauyeah.personal.springbootdatajpa.searchspecification.SearchSpecification;
import macauyeah.personal.springbootdatajpa.entitytwo.database.specification.SomethingTwoFilter;

@Service
public class QueryService {
    private static Logger LOG = LoggerFactory.getLogger(QueryService.class);
    @Autowired
    private Ref1Repo ref1Repo;
    @Autowired
    private Ref2Repo ref2Repo;
    @Autowired
    private SomethingOneRepo somethingOneRepo;
    @Autowired
    private SomethingTwoRepo somethingTwoRepo;

    @Transactional(value = "entityTwoTransactionManager")
    public void selectOneCommitTwo() {
        somethingOneRepo.count();
        Ref1 ref1 = new Ref1();
        ref1Repo.save(ref1);
        Ref2 ref2 = new Ref2();
        ref2Repo.save(ref2);
        SomethingTwo two = new SomethingTwo();
        two.setColumnOne(2);
        two.setRef1(ref1);
        two.addRef2(ref2);
        somethingTwoRepo.save(two);

        SomethingTwoFilter somethingTwoFilter = new SomethingTwoFilter();
        somethingTwoFilter.setColumnOne(2);
        Ref1Filter ref1Filter = new Ref1Filter();
        OperatorFilter<Integer> oRequest = new OperatorFilter<>();
        oRequest.setBetween(new BetweenFilter<Integer>(0, 100));
        ref1Filter.setColumnOne(oRequest);
        ref1Filter.setColumnThree("3");
        somethingTwoFilter.setRef1(ref1Filter);
        Ref2Filter ref2Filter = new Ref2Filter();
        ref2Filter.setColumnTwo("2");
        somethingTwoFilter.setRef2List(ref2Filter);
        ForeignKeyInFilter foreignKeyInSearchRequest = new ForeignKeyInFilter();
        List<BigInteger> ids = new ArrayList<>();
        ids.add(BigInteger.valueOf(1L));
        ids.add(BigInteger.valueOf(200L));
        foreignKeyInSearchRequest.setIn(ids);
        somethingTwoFilter.setRef3(foreignKeyInSearchRequest);
        List<SomethingTwo> somethingTwoList = somethingTwoRepo.findAll(SearchSpecification.deepSearchAllFields(SomethingTwo.class, somethingTwoFilter));
        for (SomethingTwo somethingTwo: somethingTwoList){
            LOG.info("somethingTwo search Result:" + somethingTwo.getId().toString());
        }
    }
}
