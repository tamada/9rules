package com.github.ninerules.parameters;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.StrictLevel;

public class IndentLevelTest {
    private IndentLevel indentLevel1 = new IndentLevel(1);
    private IndentLevel indentLevel2 = new IndentLevel(2);

    @Test
    public void testBasic(){
        assertThat(Parameters.parameter(IndentLevel.class, StrictLevel.STRICT), is(indentLevel1));
        assertThat(Parameters.parameter(IndentLevel.class, StrictLevel.GENERAL), is(indentLevel1));
        assertThat(Parameters.parameter(IndentLevel.class, StrictLevel.ROUGH), is(indentLevel2));

        assertThat(indentLevel1, is(not(new Object())));
    }
}
