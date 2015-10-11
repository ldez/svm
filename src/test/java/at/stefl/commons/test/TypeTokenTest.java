package at.stefl.commons.test;

import java.util.Set;

import org.junit.Test;

import at.stefl.commons.util.TypeToken;

public class TypeTokenTest {

    @Test
    public void should_testName() throws Exception {
        final TypeToken<?> typeToken = new TypeToken<Set<String>>() {};
        System.out.println(typeToken);
        System.out.println(typeToken.getRawType());
        System.out.println(typeToken.isAssignableFrom(String.class));
        System.out.println(typeToken.isAssignableFrom(Set.class));
        System.out.println(typeToken.isAssignableFrom(new TypeToken<Set<Integer>>() {}));
        System.out.println(typeToken.isAssignableFrom(new TypeToken<Set<String>>() {}));
    }

}