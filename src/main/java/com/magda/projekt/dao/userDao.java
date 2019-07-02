package com.magda.projekt.dao;

import com.magda.projekt.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface userDao extends CrudRepository<User, Integer> {

    public User findByLogin(String login);
    @Override
    public List< User> findAll();
}
