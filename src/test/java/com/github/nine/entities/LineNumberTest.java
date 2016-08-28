package com.github.nine.entities;

import static com.github.nine.Assert.assertThrows;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LineNumberTest {
    private LineNumber ten;
    private LineNumber nine;
    private LineNumber eleven;
    private LineNumber twelve;

    @Before
    public void setUp(){
        nine = new LineNumber(9);
        ten = new LineNumber(10);
        eleven = new LineNumber(11);
        twelve = new LineNumber(12);
    }

    @Test
    public void testBasic(){
        assertThat(ten, is(greaterThan(nine)));
        assertThat(ten, is(lessThan(eleven)));
        assertThat(ten, is(new LineNumber(10)));

        LineNumber ten2 = new LineNumber(10);
        assertThat(ten.hashCode(), is(ten2.hashCode()));
        assertThat(ten, is(not(eleven)));
        assertThat(ten, is(not(new Object())));
    }

    @Test
    public void testSequent(){
        assertThat(ten.isSequent(nine), is(true));
        assertThat(ten.isSequent(eleven), is(true));
        assertThat(ten.isSequent(twelve), is(false));
    }

    @Test
    public void testIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> new LineNumber(-1));
    }
}
