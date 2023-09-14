package com.springboot.blog.app.serviceImpl;

import com.springboot.blog.app.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    // METHOD TO UPLOAD FILE / IMAGE
    @Override
    public String uploadImage(MultipartFile file, String path) {
        if(!file.isEmpty()) {
            // file name
            String fileName = file.getOriginalFilename();

            // generate random file name
            String randomID = UUID.randomUUID().toString();
            String newFileName = randomID.concat(fileName.substring(fileName.indexOf(".")));

            String filePath = path + File.separator + newFileName;

            // create folder if not created
            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }

            // file copy

            try {
                Files.copy(file.getInputStream(), Paths.get(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return newFileName;

        }
        return "default.png";
    }

    // METHOD TO DOWNLOAD IMAGE / FILE
    @Override
    public InputStream getResource(String path, String fileName) {
        String filePath = path + File.separator + fileName;
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return is;
    }

    // METHOD TO DELETE IMAGE / FILE
    @Override
    public String deleteImageMethod(String path, String fileName) {
        String filePath = path + File.separator + fileName;
        File fileToDelete = new File(filePath);

        if(fileToDelete.exists() && fileToDelete.delete()) {
            return "Image successfully deleted!!";
        }
        return "Image doesn't exists!!";
    }
}
