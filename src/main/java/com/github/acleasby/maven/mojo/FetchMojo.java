package com.github.acleasby.maven.mojo;

import com.github.acleasby.maven.factory.AmazonS3ClientFactory;
import com.github.acleasby.maven.model.Credential;
import com.github.acleasby.maven.model.Resource;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Goal which fetches remote resources.
 */
@Mojo(name = "fetch", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class FetchMojo extends AbstractMojo {

    @Parameter(property = "credentials")
    private Credential credentials;

    @Parameter(property = "resources", required = true)
    private List<Resource> resources;

    public void execute() throws MojoExecutionException {
        AmazonS3 amazonS3 = AmazonS3ClientFactory.get(credentials);
        resources.stream().forEach(resource -> {
            getLog().info("Downloading Resource from S3");
            getLog().info("Bucket:\t\t\t\t" + resource.getBucketName());
            getLog().info("Key:\t\t\t\t" + resource.getKey());
            getLog().info("Destination:\t\t\t" + resource.getDestination());
            S3Object s3Object = amazonS3.getObject(new GetObjectRequest(resource.getBucketName(), resource.getKey()));
            try {
                if (resource.getDestination().getParent() != null && !resource.getDestination().getParentFile().exists() && !resource.getDestination().getParentFile().mkdirs()) {
                    throw new RuntimeException("Unable to create directory " + resource.getDestination().getParent());
                }
                Files.copy(s3Object.getObjectContent(), resource.getDestination().toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Unable to download " + resource.getKey() +
                        " from " + resource.getDestination(), e);
            }
        });
    }
}
