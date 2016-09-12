package com.github.ninerules.parameters;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.StrictLevel;

public class NullParameterTest {
    @Test
    public void testBasic(){
        NullParameter instance = NullParameter.parameter(); 
        assertThat(Parameters.parameter(NullParameter.class, StrictLevel.STRICT),  is(instance));
        assertThat(Parameters.parameter(NullParameter.class, StrictLevel.GENERAL), is(instance));
        assertThat(Parameters.parameter(NullParameter.class, StrictLevel.ROUGH),   is(instance));

        assertThat(instance, is(NullParameter.parameter()));
        assertThat(instance, is(not(new Object())));

        assertThat(instance.isLessThan(null), is(false));
        assertThat(instance.isGreaterThan(null), is(false));
        assertThat(instance.isEqualsTo(null), is(false));
        assertThat(instance.isEqualsTo(NullParameter.parameter()), is(true));
    }
}
