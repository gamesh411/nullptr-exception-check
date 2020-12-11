package com.ericsson.colorful;

import static edu.umd.cs.findbugs.test.CountMatcher.containsExactly;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;

import edu.umd.cs.findbugs.BugCollection;
import edu.umd.cs.findbugs.test.SpotBugsRule;
import edu.umd.cs.findbugs.test.matcher.BugInstanceMatcher;
import edu.umd.cs.findbugs.test.matcher.BugInstanceMatcherBuilder;

public class NullPointerExceptionTest {
    @Rule
    public SpotBugsRule spotbugs = new SpotBugsRule();

    private void assertNWarnings(String classFile, int n) {
        Path path = Paths.get("target/test-classes", "com.ericsson.colorful".replace('.', '/'), classFile);
        BugCollection bugCollection = spotbugs.performAnalysis(path);
        
        BugInstanceMatcher bugTypeMatcher = new BugInstanceMatcherBuilder()
                .bugType("ECD_NULLPOINTER_EXCEPTION").build();
        assertThat(bugCollection, containsExactly(n, bugTypeMatcher));
    }

    @Test
    public void testGoodCase() {
      assertNWarnings("GoodCases.class", 0);
    }

    @Test
    public void testDontCatchNullPointerException() {
        assertNWarnings("DontCatchNullPointerExceptionTest.class", 1);
    }

    @Test
    public void testDontCatchRuntimeException() {
        assertNWarnings("DontCatchRuntimeExceptionTest.class", 1);
    }

    @Test
    public void testDontCatchException() {
        assertNWarnings("DontCatchExceptionTest.class", 1);
    }

    @Test
    public void testDontCatchThrowable() {
        assertNWarnings("DontCatchThrowableTest.class", 1);
    }
}
