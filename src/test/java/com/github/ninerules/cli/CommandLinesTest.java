package com.github.ninerules.cli;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.PrintWriter;

import org.junit.Test;

import com.github.ninerules.StrictLevel;

public class CommandLinesTest {
    @Test
    public void testBasic(){
        CommandLines cli = new CommandLines("--help --general --strict src/main/java src/test/java".split(" "));

        assertThat(cli.level(), is(StrictLevel.GENERAL));

        assertThat(cli.hasOption(Option.HELP_OPTION),    is(true));
        assertThat(cli.hasOption(Option.STRICT_OPTION),  is(true));
        assertThat(cli.hasOption(Option.GENERAL_OPTION), is(true));
        assertThat(cli.hasOption(Option.ROUGH_OPTION),   is(false));

        assertThat(cli.printHelpIfSpecified(new PrintWriter(System.out)), is(true));

        Argument[] args = cli.arguments().toArray(size -> new Argument[size]);
        assertThat(args.length, is(2));

        assertThat(args[0], is(new Argument("src/main/java")));
        assertThat(args[1], is(new Argument("src/test/java")));
    }

    @Test
    public void testBasic2(){
        CommandLines cli = new CommandLines(new String[0]);

        assertThat(cli.level(), is(StrictLevel.STRICT));

        assertThat(cli.printHelpIfSpecified(new PrintWriter(System.out)), is(true));

        Argument[] args = cli.arguments().toArray(size -> new Argument[size]);
        assertThat(args.length, is(0));
    }
}
