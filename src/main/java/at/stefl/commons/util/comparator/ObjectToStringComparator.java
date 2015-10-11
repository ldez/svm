package at.stefl.commons.util.comparator;

import java.util.Comparator;

public class ObjectToStringComparator implements Comparator<Object> {

    @Override
    public int compare(final Object o1, final Object o2) {
        final String s1 = o1.toString();
        final String s2 = o2.toString();
        return s1.compareTo(s2);
    }

}