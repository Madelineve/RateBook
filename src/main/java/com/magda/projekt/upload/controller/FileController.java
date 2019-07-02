package com.magda.projekt.upload.controller;

import com.magda.projekt.upload.model.DBFile;
import com.magda.projekt.upload.payload.UploadFileResponse;
import com.magda.projekt.upload.repository.DBFileRepository;
import com.magda.projekt.upload.service.DBFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;


@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService DBFileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
                                         @RequestParam("title") String title,
                                         @RequestParam("authorName") String authorName,
                                         @RequestParam("authorSurname") String authorSurname,
                                         @RequestParam("description") String description,
                                         @RequestParam("category") String category) {

        DBFile dbFile = DBFileStorageService.storeFile(file,title,  authorName,authorSurname ,description,category);

            return new UploadFileResponse(dbFile.getFileName(), file.getContentType(), file.getSize());
        }


}
