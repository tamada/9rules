package com.github.ninerules.rules.primitive;

import static com.github.ninerules.Assert.assertAvailablePrivateConstructor;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PrimitiveCheckerTest {
    @Test
    public void testBasic(){
        assertThat(PrimitiveChecker.check("boolean"), is(true));
        assertThat(PrimitiveChecker.check("byte"),    is(true));
        assertThat(PrimitiveChecker.check("char"),    is(true));
        assertThat(PrimitiveChecker.check("short"),   is(true));
        assertThat(PrimitiveChecker.check("int"),     is(true));
        assertThat(PrimitiveChecker.check("long"),    is(true));
        assertThat(PrimitiveChecker.check("float"),   is(true));
        assertThat(PrimitiveChecker.check("double"),  is(true));

        assertThat(PrimitiveChecker.check("java.lang.String"), is(true));
        assertThat(PrimitiveChecker.check("java.util.Date"),   is(true));
        assertThat(PrimitiveChecker.check("java.lang.System"), is(false));
    }

    @Test
    public void testDefaultPrivateConstructor() throws Exception{
        assertAvailablePrivateConstructor(PrimitiveChecker.class);
    }    
}
