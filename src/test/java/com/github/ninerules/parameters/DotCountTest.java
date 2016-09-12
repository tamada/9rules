package com.github.ninerules.parameters;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.github.ninerules.StrictLevel;

public class DotCountTest {
    private DotCount count0 = new DotCount(0);
    private DotCount count1 = new DotCount(1);
    private DotCount count2 = new DotCount(2);
    private DotCount count3 = new DotCount(3);

    @Test
    public void testBasic(){
        assertThat(Parameters.parameter(DotCount.class, StrictLevel.STRICT), is(count1));
        assertThat(Parameters.parameter(DotCount.class, StrictLevel.GENERAL), is(count2));
        assertThat(Parameters.parameter(DotCount.class, StrictLevel.ROUGH), is(count3));

        assertThat(count1, is(not(new Object())));

        assertThat(count1, is(not(count2)));
    }

    @Test
    public void testIntegerParameter(){
        assertThat(count3, is(greaterThan(count0)));
        assertThat(count3, is(greaterThan(count1)));
        assertThat(count3, is(greaterThan(count2)));
        assertThat(count3, is(count3));

        assertThat(count2, is(greaterThan(count0)));
        assertThat(count2, is(greaterThan(count1)));
        assertThat(count2, is(count2));
        assertThat(count2, is(lessThan(count3)));

        assertThat(count1, is(greaterThan(count0)));
        assertThat(count1, is(count1));
        assertThat(count1, is(lessThan(count2)));
        assertThat(count1, is(lessThan(count3)));

        assertThat(count0, is(count0));
        assertThat(count0, is(lessThan(count1)));
        assertThat(count0, is(lessThan(count1)));
        assertThat(count0, is(lessThan(count1)));
    }

    @Test
    public void testSorting(){
        DotCount[] counts = new DotCount[] { count1, count3, count0, count2, };
        Arrays.sort(counts);
        assertThat(counts[0], is(count0));
        assertThat(counts[1], is(count1));
        assertThat(counts[2], is(count2));
        assertThat(counts[3], is(count3));
    }

    @Test
    public void testIntegerParameter2(){
        assertThat(count1.isEqualsTo(count1), is(true));
        assertThat(count1.isEqualsTo(new DotCount(1)), is(true));

        assertThat(count1.isEqualsTo(count2), is(false));
        assertThat(count1.isEqualsTo(new DotCount(3)), is(false));
    }
}
