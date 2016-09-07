package com.github.ninerules.parameters;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.StrictLevel;

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

        assertThat(length1, is(not(new Object())));
    }
}
