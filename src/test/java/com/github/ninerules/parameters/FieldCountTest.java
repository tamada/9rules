package com.github.ninerules.parameters;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.StrictLevel;

public class FieldCountTest {
    private FieldCount count1 = new FieldCount(1);
    private FieldCount count2 = new FieldCount(2);
    private FieldCount count3 = new FieldCount(3);
    private FieldCount count4 = new FieldCount(4);

    @Test
    public void testBasic(){
        assertThat(Parameters.parameter(FieldCount.class, StrictLevel.STRICT), is(count2));
        assertThat(Parameters.parameter(FieldCount.class, StrictLevel.GENERAL), is(count3));
        assertThat(Parameters.parameter(FieldCount.class, StrictLevel.ROUGH), is(count4));

        assertThat(count2, is(greaterThan(count1)));

        assertThat(count2, is(not(new Object())));
    }
}
