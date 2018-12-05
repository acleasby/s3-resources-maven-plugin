package com.github.acleasby.maven.model;

import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.Serializable;

public class Resource implements Serializable {
    private static final long serialVersionUID = -5041737006930072062L;

    @Parameter(property = "bucketName", required = true)
    private String bucketName;

    @Parameter(property = "key", required = true)
    private String key;

    @Parameter(property = "destination", required = true)
    private File destination;

    public String getBucketName() {
        return bucketName;
    }

    public File getDestination() {
        return destination;
    }

    public String getKey() {
        return key;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setDestination(File destination) {
        this.destination = destination;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
