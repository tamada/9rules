package com.github.ninerules.entities;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.entities.Message;
import com.github.ninerules.entities.ViolationType;
import com.github.ninerules.parameters.FieldCount;

public class ViolationTypeTest {
    @Test
    public void testBasic(){
        ViolationType type1 = new ViolationType(new Message("aaa"));
        ViolationType type2 = new ViolationType(new Message("aaa"));
        ViolationType type3 = new ViolationType(new Message("bbb"));

        assertThat(type1, is(type2));
        assertThat(type1.equals(type2), is(true));
        assertThat(type1.equals(type3), is(false));
        assertThat(type1.equals(null),  is(false));
        assertThat(type1.equals(new Object()), is(false));
        assertThat(type1.hashCode(), is(type2.hashCode()));
    }

    @Test
    public void testBasic1(){
        ViolationType type1 = new ViolationType(new Message("aaa"), null);
        ViolationType type2 = new ViolationType(new Message("aaa"), null);
        ViolationType type3 = new ViolationType(new Message("bbb"), null);

        assertThat(type1, is(type2));
        assertThat(type1.toString(), is("aaa"));
        assertThat(type1.equals(type2), is(true));
        assertThat(type1.hashCode(), is(type2.hashCode()));

        assertThat(type1.equals(type3), is(false));
        assertThat(type1.equals(null), is(false));
        assertThat(type1.equals(new Object()), is(false));
    }

    @Test
    public void testEqualsWithParameter(){
        ViolationType type1 = new ViolationType(new Message("abc"), FieldCount.GENERAL_LEVEL);
        ViolationType type2 = new ViolationType(new Message("abc"), FieldCount.ROUGH_LEVEL);
        ViolationType type3 = new ViolationType(new Message("aaa"), FieldCount.GENERAL_LEVEL);

        assertThat(type1, is(new ViolationType(new Message("abc"), FieldCount.GENERAL_LEVEL)));
        assertThat(type1.equals(type1), is(true));
        assertThat(type1.equals(type2), is(false));
        assertThat(type1.equals(type3), is(false));
    }
}
