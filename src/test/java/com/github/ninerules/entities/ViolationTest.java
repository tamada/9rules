package com.github.ninerules.entities;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.parameters.NullParameter;

public class ViolationTest {
    private Message test = new Message("test");

    @Test
    public void testEquals(){
        Violation violation = new Violation(new ViolationType(test, NullParameter.parameter()),
                LineCountsBuilder.builder().of(10).build());
        Violation violation2 = new Violation(new ViolationType(test, NullParameter.parameter()),
                LineCountsBuilder.builder().of(10).build());
        Violation violation3 = new Violation(new ViolationType(test, NullParameter.parameter()),
                LineCountsBuilder.builder().of(12).build());

        assertThat(violation, is(violation2));
        assertThat(violation, is(not(violation3)));
        assertThat(violation.hashCode(), is(violation2.hashCode()));
        assertThat(violation.hashCode(), is(not(violation3.hashCode())));
        assertThat(violation.toString(), is("line: 10, test"));
    }

    @Test
    public void testNotEquals(){
        Violation violation = new Violation(new ViolationType(test, NullParameter.parameter()),
                LineCountsBuilder.builder().of(10).build());
        
        assertThat(violation, is(not(new Violation(new ViolationType(test, NullParameter.parameter()),
                LineCountsBuilder.builder().of(15).build()))));
        assertThat(violation, is(not(new Object())));
    }
}
