package at.stefl.commons.network.mac;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Random;

public class MACAddress {

    private static final MACAddressFormat COLONED_FORMAT = new SimpleMACAddressFormat();

    public static final int SIZE = 6;

    public static final MACAddress EMPTY = new MACAddress(0);
    public static final MACAddress FULL = new MACAddress(0xffffffffffffl);

    public static final MACAddress BROADCAST = FULL;

    // TODO: fix (not every address is validate)
    public static MACAddress randomAddress() {
        final MACAddress result = new MACAddress();
        final Random random = new Random();

        for (int i = 1; i < SIZE; i++) {
            result.address[i] = (byte) random.nextInt();
        }

        return result;
    }

    private byte[] address = new byte[SIZE];

    private MACAddress() {}

    // TODO: exception
    public MACAddress(final String address) {
        try {
            this.address = COLONED_FORMAT.parseObject(address).toByteArray();
        } catch (final ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public MACAddress(final byte... address) {
        this(address, 0);
    }

    public MACAddress(final byte[] address, final int offset) {
        if ((address.length - offset) < SIZE) {
            throw new IllegalArgumentException("The address has a illegal length!");
        }

        System.arraycopy(address, offset, this.address, 0, SIZE);
    }

    public MACAddress(final int... address) {
        if (address.length != SIZE) {
            throw new IllegalArgumentException("The address has a illegal length!");
        }

        for (int i = 0; i < SIZE; i++) {
            this.address[i] = (byte) address[i];
        }
    }

    public MACAddress(final long address) {
        for (int i = 0; i < SIZE; i++) {
            this.address[i] = (byte) (address >> ((SIZE - i - 1) << 3));
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.address);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MACAddress)) {
            return false;
        }
        final MACAddress address = (MACAddress) obj;

        return Arrays.equals(this.address, address.address);
    }

    @Override
    public String toString() {
        return this.toColonedString();
    }

    public boolean isBroadcast() {
        return this.equals(BROADCAST);
    }

    public String toColonedString() {
        return COLONED_FORMAT.format(this);
    }

    public long toLong() {
        long result = 0;

        for (int i = 0; i < SIZE; i++) {
            result |= (long) (this.address[i] & 0xff) << ((SIZE - 1 - i) << 3);
        }

        return result;
    }

    public byte[] toByteArray() {
        final byte[] result = new byte[SIZE];
        System.arraycopy(this.address, 0, result, 0, SIZE);

        return result;
    }

}