package at.stefl.svm.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import at.stefl.commons.io.ByteStreamUtil;
import at.stefl.svm.enumeration.ActionType;
import at.stefl.svm.enumeration.SVMConstants;
import at.stefl.svm.object.SVMHeader;
import at.stefl.svm.object.action.FontAction;
import at.stefl.svm.object.action.SVMAction;

public class SVMReader {

    private final SVMDataInputStream in;

    private boolean headerRead;

    public SVMReader(final SVMDataInputStream in) {
        this.in = in;
    }

    public SVMReader(final InputStream in) {
        this(new SVMDataInputStream(in));
    }

    public SVMHeader readHeader() throws IOException {
        if (this.headerRead) {
            throw new IllegalStateException("header already read");
        }
        if (!ByteStreamUtil.matchBytes(this.in, SVMConstants.MAGIC_NUMBER)) {
            throw new IllegalStateException("uncorrect magic number");
        }
        final SVMHeader result = new SVMHeader().deserialize(this.in);
        this.headerRead = true;
        return result;
    }

    public SVMAction readAction() throws IOException {
        if (!this.headerRead) {
            this.readHeader();
        }

        int actionCode;

        try {
            actionCode = this.in.readUnsignedShort();
        } catch (final EOFException e) {
            return null;
        }

        final ActionType actionType = ActionType.getByCode(actionCode);
        final SVMAction result = actionType.newActionObject().deserialize(this.in);

        if (result instanceof FontAction) {
            this.in.setDefaultEncoding(((FontAction) result).getFontDefinition().getTextEncoding());
        }

        return result;
    }

}