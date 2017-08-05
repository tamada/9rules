package com.github.ninerules;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StrictLevelTest {
    @Test
    public void testBasic(){
        assertThat(StrictLevel.valueOf("STRICT"),  is(StrictLevel.STRICT));
        assertThat(StrictLevel.valueOf("GENERAL"), is(StrictLevel.GENERAL));
        assertThat(StrictLevel.valueOf("ROUGH"),   is(StrictLevel.ROUGH));

        StrictLevel[] levels = StrictLevel.values();
        assertThat(levels.length, is(3));
    }
}
