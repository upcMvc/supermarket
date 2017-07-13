package com.mvc.upc.service;

import com.mvc.upc.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lylllcc on 2017/4/17.
 */
@Service
public class FileUploadService {

    private final StorageService storageService;

    @Autowired
    public FileUploadService(StorageService storageService){
        this.storageService = storageService;
    }


    public void store(MultipartFile file, String name){
        System.out.println(this.getClass().toString()+":"+file.getOriginalFilename());
        storageService.store(file,name);
    }
}
