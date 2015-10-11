package at.stefl.commons.util;

import java.util.Arrays;

public class ObjectIdentifier {

    private static final long MAX_SUB_ID = 0xffffffffl;
    private static final long SUB_ID_MASK = 0xffffffffl;
    private static final int[] NULL_OID = { 0 };

    private final int[] value;

    public ObjectIdentifier() {
        this.value = NULL_OID;
    }

    public ObjectIdentifier(final int[] oid) {
        this.value = Arrays.copyOf(oid, oid.length);
    }

    public ObjectIdentifier(final String oid) {
        final String[] subIDs = oid.split("\\.");

        if (oid.replaceAll("\\.", "").length() != (oid.length() - subIDs.length + 1)) {
            throw new IllegalArgumentException("Separators are malformed!");
        }

        this.value = new int[subIDs.length];

        for (int i = 0; i < subIDs.length; i++) {
            final long subID = Long.parseLong(subIDs[i]);

            if (subID < 0) {
                throw new IllegalArgumentException("Sub ID cannot be negative!");
            }
            if (subID > MAX_SUB_ID) {
                throw new IllegalArgumentException("Sub ID cannot be greater than " + MAX_SUB_ID + "!");
            }

            this.value[i] = (int) subID;
        }
    }

    public ObjectIdentifier(final int[] parentOID, final int[] childOID) {
        this.value = new int[parentOID.length + childOID.length];
        System.arraycopy(parentOID, 0, this.value, 0, parentOID.length);
        System.arraycopy(childOID, 0, this.value, parentOID.length, childOID.length);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < this.value.length; i++) {
            final long subID = this.value[i] & SUB_ID_MASK;
            builder.append(subID);
            if (i < this.value.length - 1) {
                builder.append(".");
            }
        }

        return builder.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ObjectIdentifier)) {
            return false;
        }
        final ObjectIdentifier oid = (ObjectIdentifier) obj;

        return Arrays.equals(this.value, oid.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.value);
    }

    public int[] getValue() {
        return Arrays.copyOf(this.value, this.value.length);
    }

    public boolean startsWith(final ObjectIdentifier oid) {
        // TODO: or false?
        if (oid.value.length > this.value.length) {
            throw new IllegalArgumentException("The given OID is longer than this!");
        }

        for (int i = 0; i < oid.value.length; i++) {
            if (this.value[i] != oid.value[i]) {
                return false;
            }
        }

        return true;
    }

    public ObjectIdentifier append(final ObjectIdentifier oid) {
        return new ObjectIdentifier(this.value, oid.value);
    }

}