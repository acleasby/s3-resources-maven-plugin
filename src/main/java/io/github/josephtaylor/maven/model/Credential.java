package io.github.josephtaylor.maven.model;

import java.io.Serializable;

public class Credential implements Serializable {
    private static final long serialVersionUID = -3831769456529740370L;

    /**
     * @parameter expression="${credentials.profile}
     */
    private String profile;

    /**
     * @parameter expression="${credentials.accessKey}"
     */
    private String accessKey;

    /**
     * @parameter expression="${credentials.secretAccessKey}"
     */
    private String secretAccessKey;

    public String getAccessKey() {
        return accessKey;
    }

    public String getProfile() {
        return profile;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }
}
