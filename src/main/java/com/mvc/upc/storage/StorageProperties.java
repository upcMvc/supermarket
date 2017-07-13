package com.mvc.upc.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("filestorage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "image";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
