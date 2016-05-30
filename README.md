# s3-resources-maven-plugin

This is a plugin for downloading resources from S3 prior to the build process.

## Usage

### Inclusion in `pom.xml`

Include the plugin in the plugins section of your pom.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>io.github.josephtaylor.maven</groupId>
            <artifactId>s3-resources-maven-plugin</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </plugin>
    </plugins>
</build>
```

### Goals

#### `fetch`

The plugin has one goal: `fetch`, which downloads resources from S3 buckets and places them at the desired destinations.

It can be invoked with

```mvn s3-resources:fetch```

## Configuration

### AWS Credentials

By default, the plugin will use the AWS credentials set up for the `default` profile, which generally live in `~/.aws/credentials`

A different profile can be declared like so:

```xml
...
<plugin>
    <groupId>io.github.josephtaylor.maven</groupId>
    <artifactId>s3-resources-maven-plugin</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <configuration>
        <credentials>
            <profile>SomeOtherProfile</profile>
        </credentials>
    </configuration>
</plugin>
...
```

Alternatively, the `accessKey` and `secretAccessKey` properties can be set directly.
It is highly recommended to not hard-code these values in the pom file but rather to use
[properties](https://maven.apache.org/pom.html#Properties) and [maven profiles](http://maven.apache.org/guides/introduction/introduction-to-profiles.html).

For example:

```xml
...
<plugin>
    <groupId>io.github.josephtaylor.maven</groupId>
    <artifactId>s3-resources-maven-plugin</artifactId>
    <configuration>
        <credentials>
            <accessKey>${aws.accessKey}</accessKey>
            <secretAccessKey>${aws.secretAccessKey}</secretAccessKey>
        </credentials>
    </configuration>
</plugin>
...
```

### Resources

Use the resources configuration to tell the plugin what resources to download and where to put them.

Each `Resource` property has three properties:
* `bucketName` - the name of the S3 bucket where the property is located.
* `key` - the key of the resource in S3.
* `destination` - the full filepath of the destination file.

For example:

```xml
<plugin>
    <groupId>io.github.josephtaylor.maven</groupId>
    <artifactId>s3-resources-maven-plugin</artifactId>
    <configuration>
        <resources>
            <resource>
                <bucketName>my.s3.bucket</bucketName>
                <key>some-folder/application-dev.properties</key>
                <destination>${project.basedir}/application-dev.properties</destination>
            </resource>
            <resource>
                <bucketName>my.s3.bucket</bucketName>
                <key>some-folder/application-prod.properties</key>
                <destination>${project.basedir}/application-prod.properties</destination>
            </resource>
        </resources>
    </configuration>
</plugin>
```
