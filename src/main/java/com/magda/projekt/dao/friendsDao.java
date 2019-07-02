package com.magda.projekt.dao;

import com.magda.projekt.entity.Friends;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface friendsDao extends CrudRepository<Friends, Integer> {


    public List<Friends> findByUser1(Integer id);
    public List<Friends> findByUser2(Integer id);
    @Override
    public List<Friends> findAll();
}
