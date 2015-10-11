package at.stefl.svm.enumeration;

import java.util.HashSet;
import java.util.Set;

public enum MirrorFlag {

    HORIZONTALLY(MirrorFlagConstants.MTF_MIRROR_HORZ), VERTICAL(MirrorFlagConstants.MTF_MIRROR_VERT);

    private final int mask;

    public static Set<MirrorFlag> getMirrorFlagsByCode(final int code) {
        final MirrorFlag[] flags = values();
        final Set<MirrorFlag> result = new HashSet<MirrorFlag>(flags.length);

        for (final MirrorFlag flag : flags) {
            if ((code & flag.mask) != 0) {
                result.add(flag);
            }
        }

        return result;
    }

    public static int getCodeByMirrorFlags(final Set<MirrorFlag> flags) {
        int result = 0;

        for (final MirrorFlag flag : flags) {
            result |= flag.mask;
        }

        return result;
    }

    private MirrorFlag(final int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return this.mask;
    }

}