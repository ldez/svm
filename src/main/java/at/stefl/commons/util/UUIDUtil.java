package at.stefl.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class UUIDUtil {

    public static final int SIZE = 16;

    public static byte[] uuidToBytes(final UUID uuid) {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(SIZE);
        final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        try {
            dataOutputStream.writeLong(uuid.getMostSignificantBits());
            dataOutputStream.writeLong(uuid.getLeastSignificantBits());
        } catch (final IOException e) {}

        return outputStream.toByteArray();
    }

    public static UUID bytesToUuid(final byte[] uuid) {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(uuid);
        final DataInputStream dataOutputStream = new DataInputStream(inputStream);
        long mostSigBits = 0;
        long leastSigBits = 0;

        try {
            mostSigBits = dataOutputStream.readLong();
            leastSigBits = dataOutputStream.readLong();
        } catch (final IOException e) {}

        return new UUID(mostSigBits, leastSigBits);
    }

    private UUIDUtil() {}

}