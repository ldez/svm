package at.stefl.commons.network.ip;

// TODO: improve
public class SubnetMask {

    private static final int PREFIX_MIN = 0;
    private static final int PREFIX_MAX = 32;

    public static final SubnetMask EMPTY = new SubnetMask(PREFIX_MIN);
    public static final SubnetMask FULL = new SubnetMask(PREFIX_MAX);

    private final int prefix;

    public SubnetMask(final int prefix) {
        if ((prefix < PREFIX_MIN) || (prefix > PREFIX_MAX)) {
            throw new IllegalArgumentException("Illegal prefix!");
        }

        this.prefix = prefix;
    }

    public SubnetMask(final IPv4Address address) {
        long binary = address.toInt() & 0xffffffffl;
        binary = ~binary & 0xffffffffl;
        binary++;

        final int bitCount = Long.bitCount(binary);
        if (bitCount != 1) {
            throw new IllegalArgumentException("Illegal address!");
        }

        this.prefix = Long.numberOfLeadingZeros(binary) - 31;
    }

    public SubnetMask(final String address) {
        this(new IPv4Address(address));
    }

    @Override
    public String toString() {
        return this.toPrefixString();
    }

    public String toPrefixString() {
        return "/" + this.prefix;
    }

    public String toDottedString() {
        return this.toIPv4Address().toDottedString();
    }

    public int getPrefix() {
        return this.prefix;
    }

    public int toInt() {
        return (int) ~((1l << (PREFIX_MAX - this.prefix)) - 1);
    }

    public IPv4Address toIPv4Address() {
        return new IPv4Address(this.toInt());
    }

}