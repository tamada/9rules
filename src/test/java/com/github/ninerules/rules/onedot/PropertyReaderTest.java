package com.github.ninerules.rules.onedot;

import static com.github.ninerules.Assert.assertThrows;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class PropertyReaderTest {
    private URL location;

    @Before
    public void setUp() throws Exception{
        location = getClass().getResource("/resources/StringFilter.properties");
    }

    @Test
    public void testFilter(){
        PropertyReader reader = new PropertyReader(location);
        Map<String, String> map = reader.read();

        assertThat(map.get("this\\."), is(""));
        assertThat(map.get("\\\".*\\\""), is("\"\""));
    }

    @Test()
    public void testReadFailed() throws Exception{
        PropertyReader reader = new PropertyReader(new URL("file:///resources/null.properties"));
        Map<String, String> map = reader.read();

        assertThat(map.size(), is(0));
    }

    @Test
    public void testNullPointerException(){
        PropertyReader reader = new PropertyReader(null);
        assertThrows(NullPointerException.class, () -> reader.read());
    }
}
