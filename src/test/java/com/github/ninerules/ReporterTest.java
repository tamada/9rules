package com.github.ninerules;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCountsBuilder;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;
import com.github.ninerules.rules.results.Results;

public class ReporterTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Reporter reporter = new Reporter(new PrintWriter(out));
    private Results results;

    @Before
    public void setUp(){
        ViolationType type = new ViolationType("test");
        results = new Results(
            new FileName("test.java"), 
            Arrays.asList(
                new Violation(type, LineCountsBuilder.build(10)),
                new Violation(type, LineCountsBuilder.build(15, 16))
            )
        );
        reporter.report(results);
    }

    @Test
    public void testBasic(){
        String result = new String(out.toByteArray()).trim();
        assertThat(result, is(String.join(
            System.getProperty("line.separator"),
            "test.java", "line: 10, test", "line: 15,16, test"
        )));
    }
}
