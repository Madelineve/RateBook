package com.magda.projekt.dao;

import com.magda.projekt.entity.Rates;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ratesDao extends CrudRepository<Rates, Integer> {


    public List<Rates> findByUser(Integer user);
    @Override
    public List<Rates> findAll();


/*
    @Modifying
    @Query("update Rates r set r.rate= ?1 where r.id = ?2")
    public void setRateInfoById(float rate, Integer id);
*/

}
