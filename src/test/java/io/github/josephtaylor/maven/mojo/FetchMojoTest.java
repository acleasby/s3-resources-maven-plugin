package io.github.josephtaylor.maven.mojo;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

public class FetchMojoTest extends AbstractMojoTestCase {

    protected void setUp() throws Exception {
        super.setUp();
        File testFile = new File("/tmp/test.txt");
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * @throws Exception if any
     */
    public void testSomething()
            throws Exception {
        File pom = getTestFile("src/test/resources/unit/project-to-test/pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        FetchMojo fetchMojo = (FetchMojo) lookupMojo("fetch", pom);
        assertNotNull(fetchMojo);
        fetchMojo.execute();

        File testFile = new File("/tmp/test.txt");
        assertTrue(testFile.exists());
    }
}