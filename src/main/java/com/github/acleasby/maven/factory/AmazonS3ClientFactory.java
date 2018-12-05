package com.github.acleasby.maven.factory;

import com.github.acleasby.maven.model.Credential;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.codehaus.plexus.util.StringUtils;

public class AmazonS3ClientFactory {
    private AmazonS3ClientFactory() {
        throw new UnsupportedOperationException("This is a static factory.");
    }
    public static AmazonS3 get(final Credential credentials) {
        if (null == credentials || (StringUtils.isBlank(credentials.getProfile())
                && StringUtils.isBlank(credentials.getAccessKey()))) {
            return new AmazonS3Client();
        }
        if (StringUtils.isNotBlank(credentials.getProfile())) {
            return new AmazonS3Client(new ProfileCredentialsProvider(credentials.getProfile()));
        }
        if (StringUtils.isBlank(credentials.getAccessKey())) {
            throw new RuntimeException("No AWS access key provided.");
        }
        if (StringUtils.isBlank(credentials.getSecretAccessKey())) {
            throw new RuntimeException("No AWS secret access key provided.");
        }
        System.setProperty("aws.accessKeyId", credentials.getAccessKey());
        System.setProperty("aws.secretKey", credentials.getSecretAccessKey());
        return new AmazonS3Client(new SystemPropertiesCredentialsProvider());
    }
}
