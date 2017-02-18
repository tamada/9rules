package com.github.ninerules.parameters;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.StrictLevel;

public class MethodLengthTest {
    private MethodLength length1 = new MethodLength(3);
    private MethodLength length2 = new MethodLength(5);
    private MethodLength length3 = new MethodLength(10);
    private MethodLength length4 = new MethodLength(6);

    @Test
    public void testBasic(){
        assertThat(Parameters.parameter(MethodLength.class, StrictLevel.STRICT),  is(length1));
        assertThat(Parameters.parameter(MethodLength.class, StrictLevel.GENERAL), is(length2));
        assertThat(Parameters.parameter(MethodLength.class, StrictLevel.ROUGH),   is(length3));

        assertThat(length2, is(lessThan(length3)));
        assertThat(length4, is(greaterThan(length1)));

        assertThat(length1, is(new MethodLength(3)));
        assertThat(length1, is(not(new Object())));
        assertThat(length1, is(not(length2)));
        assertThat(length1, is(not(new SourceLength(3))));
    }
}
