package com.github.ninerules.rules;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.entities.LineCountsBuilder;

public class ViolationTest {
    private ViolationType test = new ViolationType("test");

    @Test
    public void testEquals(){
        Violation violation = new Violation(test, LineCountsBuilder.build(10));

        assertThat(violation, is(new Violation(test, LineCountsBuilder.build(10))));
        assertThat(violation.toString(), is("line: 10, test"));
    }

    @Test
    public void testNotEquals(){
        Violation violation = new Violation(test, LineCountsBuilder.build(10));
        
        assertThat(violation, is(not(new Violation(test, LineCountsBuilder.build(15)))));
        assertThat(violation, is(not(new Object())));
    }
}
