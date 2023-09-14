package com.springboot.blog.app.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {

    String uploadImage(MultipartFile file, String path);

    InputStream getResource(String path, String fileName);

    String deleteImageMethod(String path, String fleName);
}
