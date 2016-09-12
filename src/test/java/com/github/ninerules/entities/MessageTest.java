package com.github.ninerules.entities;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MessageTest {
    @Test
    public void testBasic(){
        Message name = new Message("message1");

        assertThat(name.toString(), is("message1"));
        assertThat(name, is(new Message("message1")));

        assertThat(name, is(not(new Message("message2"))));
        assertThat(name, is(not(new Object())));
    }

    @Test
    public void testFormatter(){
        Message message = new Message("format: %s");

        assertThat(message.format("hoge"), is("format: hoge"));
    }
}
