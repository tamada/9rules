package com.github.ninerules;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class MainTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setUp(){
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testSrcTestResourcesHello() throws Exception{
        Main.main(new String[] { "src/test/resources/hello/src/main/java" });

        String output = new String(out.toByteArray());
        String[] lines = output.split(System.getProperty("line.separator"));

        assertThat(lines.length, is(18));
        assertThat(lines[ 0], is("src/test/resources/hello/src/main/java/sample/hello/GodObject.java"));
        assertThat(lines[ 1], is("line: 53, else statement found."));
        assertThat(lines[ 2], is("line: 13,14,15,16, field count is more than 2."));
        assertThat(lines[ 3], is("line: 13,14,15,16, not first class collection."));
        assertThat(lines[ 4], is("line: 43, indentation level is too much (more than 1 indent level)."));
        assertThat(lines[ 5], is("line: 28, many dots per line (more than 1 dots)."));
        assertThat(lines[ 6], is("line: 29, many dots per line (more than 1 dots)."));
        assertThat(lines[ 7], is("line: 13,14,15,16, no primitives."));
        assertThat(lines[ 8], is("line: 43, method is too long (over 3 lines)."));
        assertThat(lines[ 9], is("line: 65, source code is too long (over 50 lines)."));
        assertThat(lines[10], is("src/test/resources/hello/src/main/java/sample/hello/GodObjectButNotTarget.java"));
        assertThat(lines[11], is("src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java"));
        assertThat(lines[12], is("line: 10, setter method found."));
        assertThat(lines[13], is("line: 14, getter method found."));
        assertThat(lines[14], is("line: 18, method is too long (over 3 lines)."));
        assertThat(lines[15], is("src/test/resources/hello/src/main/java/sample/hello/Launcher.java"));
        assertThat(lines[16], is("line: 10, method is too long (over 3 lines)."));
        assertThat(lines[17], is("9rules found 13 violations in 3 files."));
    }
    @Test
    public void testSrcTestResourcesHelloWithNoSummary() throws Exception{
        Main.main(new String[] {"--no-summary", "src/test/resources/hello/src/main/java" });

        String output = new String(out.toByteArray());
        String[] lines = output.split(System.getProperty("line.separator"));

        assertThat(lines.length, is(17));
        assertThat(lines[ 0], is("src/test/resources/hello/src/main/java/sample/hello/GodObject.java"));
        assertThat(lines[ 1], is("line: 53, else statement found."));
        assertThat(lines[ 2], is("line: 13,14,15,16, field count is more than 2."));
        assertThat(lines[ 3], is("line: 13,14,15,16, not first class collection."));
        assertThat(lines[ 4], is("line: 43, indentation level is too much (more than 1 indent level)."));
        assertThat(lines[ 5], is("line: 28, many dots per line (more than 1 dots)."));
        assertThat(lines[ 6], is("line: 29, many dots per line (more than 1 dots)."));
        assertThat(lines[ 7], is("line: 13,14,15,16, no primitives."));
        assertThat(lines[ 8], is("line: 43, method is too long (over 3 lines)."));
        assertThat(lines[ 9], is("line: 65, source code is too long (over 50 lines)."));
        assertThat(lines[10], is("src/test/resources/hello/src/main/java/sample/hello/GodObjectButNotTarget.java"));
        assertThat(lines[11], is("src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java"));
        assertThat(lines[12], is("line: 10, setter method found."));
        assertThat(lines[13], is("line: 14, getter method found."));
        assertThat(lines[14], is("line: 18, method is too long (over 3 lines)."));
        assertThat(lines[15], is("src/test/resources/hello/src/main/java/sample/hello/Launcher.java"));
        assertThat(lines[16], is("line: 10, method is too long (over 3 lines)."));
    }

    @Test
    public void testHelpMessage() throws Exception{
        Main.main(new String[] { "--help" });

        String output = new String(out.toByteArray());
        String[] lines = output.split(System.getProperty("line.separator"));

        assertThat(lines.length, is(13));
        assertThat(lines[0], is("9rules version 1.1.0"));
        assertThat(lines[1], is("java -jar 9rules.jar [OPTIONS] <ARGUMENTS...>"));
        assertThat(lines[2], is(""));
        assertThat(lines[3], is("OPTIONS:"));
        assertThat(lines[4], is("    --strict        Strictly level check (Default)."));
        assertThat(lines[5], is("    --general       General level check."));
        assertThat(lines[6], is("    --rough         Rough level check."));
        assertThat(lines[7], is(""));
        assertThat(lines[8], is("    --no-summary    Print no summary."));
        assertThat(lines[9], is("    --help          Print this message and exit."));
        assertThat(lines[10], is(""));
        assertThat(lines[11], is("ARGUMENTS:"));
        assertThat(lines[12], is("    Directories include Java source files, and Java source files."));
    }
}
