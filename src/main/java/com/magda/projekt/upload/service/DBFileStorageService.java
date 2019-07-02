package com.magda.projekt.upload.service;

import com.magda.projekt.upload.exception.FileStorageException;
import com.magda.projekt.upload.exception.MyFileNotFoundException;
import com.magda.projekt.upload.model.DBFile;
import com.magda.projekt.upload.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DBFileStorageService  {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file,
                            String title,
                            String authorName,
                            String authorSurname,
                            String description,
                            String category) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            dbFile.setTitle(title);
            dbFile.setAuthorName(authorName);
            dbFile.setAuthorSurname(authorSurname);
            dbFile.setDescription(description);
            dbFile.setCategory(category);

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(Integer fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }



   /* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id_token;
        String email;

        id_token=request.getParameter("title");

        HttpSession session=request.getSession(true);

        try {
            title+=id_token;

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
*/

}
