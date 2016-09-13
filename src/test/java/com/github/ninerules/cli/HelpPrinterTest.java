package com.github.ninerules.cli;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class HelpPrinterTest {
    private HelpPrinter printer = new HelpPrinter();

    @Test
    public void testBasic(){
        CommandLines cli = CommandLines.parse("--help --strict src/main/java".split(" "));
        assertThat(printer.printIfSpecified(cli), is(true));
    }

    @Test
    public void testNotPrint(){
        CommandLines cli = CommandLines.parse("--strict src/main/java".split(" "));
        assertThat(printer.printIfSpecified(cli), is(false));
    }
}
