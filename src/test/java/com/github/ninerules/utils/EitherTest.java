package com.github.ninerules.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.Optional;

public class EitherTest {
    @Test
    public void testBasic() {
        Either<Exception, String> either = Either.ofValue("string");
        String[] values = either.stream().toArray(size -> new String[size]);

        assertThat(either.value().isPresent(), is(true));
        assertThat(either.exception().isPresent(), is(false));
        assertThat(values.length, is(1));
        assertThat(values[0], is("string"));
    }

    @Test
    public void testException() {
        Either<Exception, String> either = Either.ofException(new Exception("exception"));
        assertThat(either.value().isPresent(), is(false));
        assertThat(either.exception().isPresent(), is(true));
        assertThat(either.stream().count(), is(0L));
    }

    @Test
    public void testMap() {
        Either<Exception, Integer> either = Either.ofValue(3);
        Either<Exception, String> either2 = either.map(str -> "" + str);
        assertThat(either2.value().get(), is("3"));

        Integer value = either.map((e, v) -> v * 3);
        assertThat(value, is(9));

        Either<Exception, Integer> either3 = either.map(e -> e, v -> v * 4);
        assertThat(either3.value().isPresent(), is(true));
        assertThat(either3.exception().isPresent(), is(false));
        assertThat(either3.stream().count(), is(1L));
        assertThat(either3.value().get(), is(12));
    }
}
