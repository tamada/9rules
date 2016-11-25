package com.github.ninerules.rules.violations;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.entities.Message;

public class ViolationTypeTest {
    @Test
    public void testBasic(){
        ViolationType type1 = new ViolationType(new Message("aaa"));
        ViolationType type2 = new ViolationType(new Message("aaa"));

        assertThat(type1, is(type2));
        assertThat(type1.equals(type2), is(true));
    }

    @Test
    public void testBasic1(){
        ViolationType type1 = new ViolationType(new Message("aaa"), null);
        ViolationType type2 = new ViolationType(new Message("aaa"), null);

        assertThat(type1, is(type2));
        assertThat(type1.toString(), is("aaa"));
        assertThat(type1.equals(type2), is(true));
    }
}
