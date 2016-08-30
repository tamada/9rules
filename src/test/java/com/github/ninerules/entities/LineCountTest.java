package com.github.ninerules.entities;

import static com.github.ninerules.Assert.assertThrows;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LineCountTest {
    private LineCount ten;
    private LineCount ninerules;
    private LineCount eleven;
    private LineCount twelve;

    @Before
    public void setUp(){
        ninerules = new LineCount(9);
        ten = new LineCount(10);
        eleven = new LineCount(11);
        twelve = new LineCount(12);
    }

    @Test
    public void testBasic(){
        assertThat(ten, is(greaterThan(ninerules)));
        assertThat(ten, is(lessThan(eleven)));
        assertThat(ten, is(new LineCount(10)));

        LineCount ten2 = new LineCount(10);
        assertThat(ten.hashCode(), is(ten2.hashCode()));
        assertThat(ten, is(not(eleven)));
        assertThat(ten, is(not(new Object())));
    }

    @Test
    public void testSequent(){
        assertThat(ten.isSequent(ninerules), is(true));
        assertThat(ten.isSequent(eleven), is(true));
        assertThat(ten.isSequent(twelve), is(false));

        assertThat(ten.difference(twelve), is(new LineCount(2)));
    }

    @Test
    public void testIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> new LineCount(-1));
    }
}
