package com.github.ninerules.parameters;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.Message;

public class SourceLengthTest {
    private SourceLength length1 = new SourceLength(50);
    private SourceLength length2 = new SourceLength(70);
    private SourceLength length3 = new SourceLength(100);
    private SourceLength length4 = new SourceLength(30);

    @Test
    public void testBasic(){
        assertThat(Parameters.parameter(SourceLength.class, StrictLevel.STRICT),  is(length1));
        assertThat(Parameters.parameter(SourceLength.class, StrictLevel.GENERAL), is(length2));
        assertThat(Parameters.parameter(SourceLength.class, StrictLevel.ROUGH),   is(length3));

        assertThat(length2, is(lessThan(length3)));
        assertThat(length1, is(greaterThan(length4)));
        assertThat(length1.compareTo(length1), is(0));

        assertThat(length1, is(not(length2)));
        assertThat(length1, is(not(new Object())));
        assertThat(length1, is(not(new MethodLength(50))));
    }

    @Test
    public void testFormat(){
        Message message = new Message("line: %d");
        assertThat(length1.format(message), is("line: 50"));
        assertThat(length2.format(message), is("line: 70"));
        assertThat(length3.format(message), is("line: 100"));
        assertThat(length4.format(message), is("line: 30"));
    }
}
