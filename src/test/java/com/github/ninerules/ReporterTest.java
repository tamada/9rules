package com.github.ninerules;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCountsBuilder;
import com.github.ninerules.entities.Message;
import com.github.ninerules.parameters.NullParameter;
import com.github.ninerules.parameters.Parameter;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.rules.violations.ViolationType;

public class ReporterTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Reporter reporter;
    private Results results;

    @Before
    public void setUp() throws IOException{
        reporter = new Reporter(new PrintWriter(out));
        Message type = new Message("test");
        Parameter parameter = NullParameter.parameter();
        results = new Results(
            new FileName("test.java"), 
            Arrays.asList(
                new Violation(new ViolationType(type, parameter), LineCountsBuilder.builder().of(10).build()),
                new Violation(new ViolationType(type, parameter), LineCountsBuilder.builder().of(15, 16).build())
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
