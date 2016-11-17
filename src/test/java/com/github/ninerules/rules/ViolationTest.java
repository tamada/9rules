package com.github.ninerules.rules;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.entities.LineCountsBuilder;
import com.github.ninerules.entities.Message;
import com.github.ninerules.parameters.NullParameter;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.rules.violations.ViolationType;

public class ViolationTest {
    private Message test = new Message("test");

    @Test
    public void testEquals(){
        Violation violation = new Violation(new ViolationType(test, NullParameter.parameter()),
                LineCountsBuilder.build(builder -> builder.of(10)));

        assertThat(violation, is(new Violation(new ViolationType(test, NullParameter.parameter()),
                LineCountsBuilder.build(builder -> builder.of(10)))));
        assertThat(violation.toString(), is("line: 10, test"));
    }

    @Test
    public void testNotEquals(){
        Violation violation = new Violation(new ViolationType(test, NullParameter.parameter()),
                LineCountsBuilder.build(builder -> builder.of(10)));
        
        assertThat(violation, is(not(new Violation(new ViolationType(test, NullParameter.parameter()),
                LineCountsBuilder.build(builder -> builder.of(15))))));
        assertThat(violation, is(not(new Object())));
    }
}
