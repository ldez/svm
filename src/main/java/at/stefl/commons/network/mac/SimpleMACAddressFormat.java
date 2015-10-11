package at.stefl.commons.network.mac;

import java.text.FieldPosition;
import java.text.ParsePosition;

public class SimpleMACAddressFormat extends MACAddressFormat {

    private static final long serialVersionUID = -1900400344119211331L;

    private static final int DIGIT_COUNT = 12;
    private static final String DEFAULT_PATTERN = "xx:xx:xx:xx:xx:xx";

    private final String pattern;

    public SimpleMACAddressFormat() {
        this(DEFAULT_PATTERN);
    }

    public SimpleMACAddressFormat(final String pattern) {
        final int digitCount = pattern.length() - pattern.toLowerCase().replaceAll("x", "").length();
        if (digitCount != DIGIT_COUNT) {
            throw new IllegalArgumentException("Illegal digit count!");
        }

        this.pattern = pattern;
    }

    @Override
    public StringBuffer format(final MACAddress address, final StringBuffer toAppendTo, final FieldPosition pos) {
        final String hexValue = Long.toHexString(address.toLong());
        int hexIndex = 0;

        for (int i = 0; i < this.pattern.length(); i++) {
            final char c = this.pattern.charAt(i);

            if (Character.toLowerCase(c) == 'x') {
                char hexChar;
                if (hexIndex + hexValue.length() < DIGIT_COUNT) {
                    hexChar = '0';
                } else {
                    hexChar = hexValue.charAt(hexIndex + hexValue.length() - DIGIT_COUNT);
                }

                if (Character.isUpperCase(c)) {
                    hexChar = Character.toUpperCase(hexChar);
                }
                toAppendTo.append(hexChar);
                hexIndex++;
            } else {
                toAppendTo.append(c);
            }
        }

        return toAppendTo;
    }

    @Override
    public MACAddress parseObject(final String source, final ParsePosition pos) {
        String hexString = "";

        if (source.length() != this.pattern.length()) {
            throw new IllegalArgumentException("The length of the given string is illegal!");
        }

        for (int i = 0; i < source.length(); i++) {
            final char c = source.charAt(i);
            final char patternChar = this.pattern.charAt(i);

            if (Character.toLowerCase(patternChar) == 'x') {
                hexString += c;
            }

            pos.setIndex(i);
        }

        return new MACAddress(Long.parseLong(hexString, 16));
    }

}