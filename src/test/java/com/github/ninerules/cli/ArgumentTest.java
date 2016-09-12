package com.github.ninerules.cli;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.nio.file.Paths;

import org.junit.Test;

public class ArgumentTest {
    private Argument argument = new Argument("pom.xml");

    @Test
    public void testBasic(){
        assertThat(argument, is(new Argument("pom.xml")));
        assertThat(argument, is(not(new Argument("build.xml"))));
        assertThat(argument, is(not(new Object())));

        assertThat(argument.toPath(), is(Paths.get("pom.xml")));

        assertThat(new Argument("build.xml").toPath(), is(Paths.get("build.xml")));
    }
}
