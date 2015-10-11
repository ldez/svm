package at.stefl.commons.network.ip;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.regex.Pattern;

// TODO: improve
public class IPv4Address extends IPAddress {

    private static final String SEPARATOR = ".";
    private static final Pattern SEPARATOR_PATTERN = Pattern.compile(SEPARATOR, Pattern.LITERAL);

    public static final int SIZE = 4;

    public static final IPv4Address EMPTY = new IPv4Address(0);
    public static final IPv4Address FULL = new IPv4Address(0xffffffff);

    public static final IPv4Address LOCALHOST = new IPv4Address("127.0.0.1");

    private int address;

    public IPv4Address(final int address) {
        this.address = address;
    }

    public IPv4Address(final byte... address) {
        this(address, 0);
    }

    public IPv4Address(final byte[] address, final int offset) {
        for (int i = 0; i < SIZE; i++) {
            this.setEmptyQuad(i, address[offset + i] & 0xff);
        }
    }

    public IPv4Address(final int... address) {
        for (int i = 0; i < SIZE; i++) {
            this.setEmptyQuad(i, address[i] & 0xff);
        }
    }

    public IPv4Address(final String... address) {
        for (int i = 0; i < SIZE; i++) {
            final int quad = Integer.parseInt(address[i]);
            if ((quad < 0) || (quad > 0xff)) {
                throw new IllegalArgumentException("Malformed address!");
            }
            this.setEmptyQuad(i, quad);
        }
    }

    public IPv4Address(final String addressString) {
        this(SEPARATOR_PATTERN.split(addressString));
    }

    public IPv4Address(final Inet4Address address) {
        this(address.getAddress());
    }

    public IPv4Address(final InetAddress address) {
        this((Inet4Address) address);
    }

    @Override
    public int hashCode() {
        return this.address;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof IPv4Address)) {
            return false;
        }
        final IPv4Address address = (IPv4Address) obj;

        return this.address == address.address;
    }

    public int getQuad(final int quad) {
        return (this.address >> ((SIZE - quad - 1) << 3)) & 0xff;
    }

    private void setEmptyQuad(final int quad, final int value) {
        this.address |= value << ((SIZE - quad - 1) << 3);
    }

    @Override
    public String toString() {
        return this.toDottedString();
    }

    public String toDottedString() {
        String addressString = "";

        for (int i = 0; i < SIZE; i++) {
            addressString += this.getQuad(i);
            if (i < (SIZE - 1)) {
                addressString += SEPARATOR;
            }
        }

        return addressString;
    }

    public int toInt() {
        return this.address;
    }

    @Override
    public byte[] toByteArray() {
        final byte[] result = new byte[SIZE];

        for (int i = 0; i < SIZE; i++) {
            result[i] = (byte) this.getQuad(i);
        }

        return result;
    }

    @Override
    public Inet4Address toInetAddress() {
        return (Inet4Address) super.toInetAddress();
    }

}