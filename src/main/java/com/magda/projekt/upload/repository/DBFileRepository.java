package com.magda.projekt.upload.repository;

import com.magda.projekt.upload.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Integer> {


    public List<DBFile> findByTitle(String title);
    public List<DBFile> findByAuthorName(String name);
    public List<DBFile> findByCategory(String category);
    @Override
    public List<DBFile> findAll();
}
