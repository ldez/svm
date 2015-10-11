package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class TextLanguageAction extends SVMAction {

    private int language;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("TextLanguageAction [language=");
        builder.append(this.language);
        builder.append("]");
        return builder.toString();
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(final int language) {
        this.language = language;
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writeUnsignedShort(this.language);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.language = in.readUnsignedShort();
    }

}