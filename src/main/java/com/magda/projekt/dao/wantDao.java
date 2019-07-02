package com.magda.projekt.dao;

import com.magda.projekt.entity.WantToRead;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface wantDao extends CrudRepository<WantToRead, Integer> {

    public List<WantToRead> findByUser(Integer id);
    @Override
    public List<WantToRead> findAll();

}
