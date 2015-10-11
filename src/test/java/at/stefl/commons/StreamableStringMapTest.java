package at.stefl.commons;

import java.io.StringReader;

import org.junit.Test;

import at.stefl.commons.io.StreamableStringMap;

public class StreamableStringMapTest {

    @Test
    public void should_testName() throws Exception {
        final String source = "hallo";
        final StringReader in = new StringReader(source);

        final StreamableStringMap<String> map = new StreamableStringMap<String>();
        map.put("hallo", "hi.");
        map.put("not?", "not.");

        System.out.println(map.match(in));
    }

}