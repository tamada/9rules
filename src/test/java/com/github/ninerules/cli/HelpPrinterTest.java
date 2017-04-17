package com.github.ninerules.cli;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.PrintWriter;

import org.junit.Test;

public class HelpPrinterTest {
    private HelpPrinter printer = new HelpPrinter(new PrintWriter(System.out));

    @Test
    public void testBasic(){
        CommandLines cli = new CommandLines("--help --strict src/main/java".split(" "));
        assertThat(printer.printIfSpecified(cli), is(true));
    }

    @Test
    public void testNotPrint(){
        CommandLines cli = new CommandLines("--strict src/main/java".split(" "));
        assertThat(printer.printIfSpecified(cli), is(false));
    }
}
