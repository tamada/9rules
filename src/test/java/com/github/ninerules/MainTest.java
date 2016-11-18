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

        assertThat(lines.length, is(17));
        assertThat(lines[ 0], is("src/test/resources/hello/src/main/java/sample/hello/GodObject.java"));
        assertThat(lines[ 1], is("line: 53, else statement found."));
        assertThat(lines[ 2], is("line: 13,14,15,16, field count is more than 2"));
        assertThat(lines[ 3], is("line: 13,14,15,16, not first class collection."));
        assertThat(lines[ 4], is("line: 43, Indentation level is too much (more than 1 indent level)"));
        assertThat(lines[ 5], is("line: 28, Many dots per line (more than 1 dots)"));
        assertThat(lines[ 6], is("line: 29, Many dots per line (more than 1 dots)"));
        assertThat(lines[ 7], is("line: 13,14,15,16, no primitives."));
        assertThat(lines[ 8], is("line: 43, method is too long (over 3 lines)."));
        assertThat(lines[ 9], is("line: 65, source code is too long (over 50 lines)."));
        assertThat(lines[10], is("src/test/resources/hello/src/main/java/sample/hello/GodObjectButNotTarget.java"));
        assertThat(lines[11], is("src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java"));
        assertThat(lines[12], is("line: 10, setter method found"));
        assertThat(lines[13], is("line: 14, getter method found"));
        assertThat(lines[14], is("line: 18, method is too long (over 3 lines)."));
        assertThat(lines[15], is("src/test/resources/hello/src/main/java/sample/hello/Launcher.java"));
        assertThat(lines[16], is("line: 10, method is too long (over 3 lines)."));
    }

    @Test
    public void testHelpMessage() throws Exception{
        Main.main(new String[] { "--help" });

        String output = new String(out.toByteArray());
        String[] lines = output.split(System.getProperty("line.separator"));

        assertThat(lines.length, is(10));
        assertThat(lines[0], is("java -jar 9rules.jar [OPTIONS] <ARGUMENTS...>"));
        assertThat(lines[1], is(""));
        assertThat(lines[2], is("OPTIONS:"));
        assertThat(lines[3], is("    --strict:  Strictly level check.  If not the level options were not specified, this option is specified.")); 
        assertThat(lines[4], is("    --general: General level check."));
        assertThat(lines[5], is("    --rough:   Rough level check."));
        assertThat(lines[6], is("    --help:    Print this message and exit."));
        assertThat(lines[7], is(""));
        assertThat(lines[8], is("ARGUMENTS:"));
        assertThat(lines[9], is("    Directory includes source files."));
    }
}
