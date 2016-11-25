package com.github.ninerules.rules;

import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import org.junit.Test;

import com.github.ninerules.rules.stringvisitor.StringLineVisitorHelper;

public class StringLineVisitorHelperTest {
    @Test
    public void testException(){
        StringLineVisitorHelper helper = new StringLineVisitorHelper(null);
        helper.visit(Paths.get("/no/exsits/path"));

        assertTrue(true);
    }
}
