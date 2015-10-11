package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;
import at.stefl.svm.object.basic.FontDefinition;

public class FontAction extends SVMAction {

    private FontDefinition fontDefinition;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FontAction [fontDefinition=");
        builder.append(this.fontDefinition);
        builder.append("]");
        return builder.toString();
    }

    public FontDefinition getFontDefinition() {
        return this.fontDefinition;
    }

    public void setFontDefinition(final FontDefinition fontDefinition) {
        this.fontDefinition = fontDefinition;
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        this.fontDefinition.serialize(out);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.fontDefinition = new FontDefinition();
        this.fontDefinition.deserialize(in);
    }

}