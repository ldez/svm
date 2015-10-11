package at.stefl.svm.object.basic;

import java.io.IOException;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.commons.util.PrimitiveUtil;
import at.stefl.svm.enumeration.TextEncoding;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;
import at.stefl.svm.object.SVMVersionObject;

public class FontDefinition extends SVMVersionObject {

    private String familyName;
    private String styleName;
    private Vector2i size;
    private int charset;
    private int family;
    private int pitch;
    private int weigth;
    private int underline;
    private int strikeout;
    private int italic;
    private int language;
    private int width;
    private int orientation;
    private boolean wordline;
    private boolean outline;
    private boolean shadow;
    private byte kerning;

    private byte relief;
    private int cjkLanguage;
    private boolean vertical;
    private int emphasisMark;

    private int overline;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FontDefinition [familyName=");
        builder.append(this.familyName);
        builder.append(", styleName=");
        builder.append(this.styleName);
        builder.append(", size=");
        builder.append(this.size);
        builder.append(", charset=");
        builder.append(this.charset);
        builder.append(", family=");
        builder.append(this.family);
        builder.append(", pitch=");
        builder.append(this.pitch);
        builder.append(", weigth=");
        builder.append(this.weigth);
        builder.append(", underline=");
        builder.append(this.underline);
        builder.append(", strikeout=");
        builder.append(this.strikeout);
        builder.append(", italic=");
        builder.append(this.italic);
        builder.append(", language=");
        builder.append(this.language);
        builder.append(", width=");
        builder.append(this.width);
        builder.append(", orientation=");
        builder.append(this.orientation);
        builder.append(", wordline=");
        builder.append(this.wordline);
        builder.append(", outline=");
        builder.append(this.outline);
        builder.append(", shadow=");
        builder.append(this.shadow);
        builder.append(", kerning=");
        builder.append(this.kerning);
        builder.append(", relief=");
        builder.append(this.relief);
        builder.append(", cjkLanguage=");
        builder.append(this.cjkLanguage);
        builder.append(", vertical=");
        builder.append(this.vertical);
        builder.append(", emphasisMark=");
        builder.append(this.emphasisMark);
        builder.append(", overline=");
        builder.append(this.overline);
        builder.append("]");
        return builder.toString();
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getStyleName() {
        return this.styleName;
    }

    public Vector2i getSize() {
        return this.size;
    }

    public int getCharset() {
        return this.charset;
    }

    public TextEncoding getTextEncoding() {
        return TextEncoding.getByCode(this.getCharset());
    }

    public int getFamily() {
        return this.family;
    }

    public int getPitch() {
        return this.pitch;
    }

    public int getWeigth() {
        return this.weigth;
    }

    public int getUnderline() {
        return this.underline;
    }

    public int getStrikeout() {
        return this.strikeout;
    }

    public int getItalic() {
        return this.italic;
    }

    public int getLanguage() {
        return this.language;
    }

    public int getWidth() {
        return this.width;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean isWordline() {
        return this.wordline;
    }

    public boolean isOutline() {
        return this.outline;
    }

    public boolean isShadow() {
        return this.shadow;
    }

    public byte getKerning() {
        return this.kerning;
    }

    public byte getRelief() {
        return this.relief;
    }

    public int getCJKLanguage() {
        return this.cjkLanguage;
    }

    public boolean isVertical() {
        return this.vertical;
    }

    public int getEmphasisMark() {
        return this.emphasisMark;
    }

    public int getOverline() {
        return this.overline;
    }

    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    public void setStyleName(final String styleName) {
        this.styleName = styleName;
    }

    public void setSize(final Vector2i size) {
        this.size = size;
    }

    public void setCharset(final int charset) {
        PrimitiveUtil.checkUnsignedShort(charset);
        this.charset = charset;
    }

    public void setTextEncoding(final TextEncoding textEncoding) {
        this.setCharset(textEncoding.getCode());
    }

    public void setFamily(final int family) {
        PrimitiveUtil.checkUnsignedShort(family);
        this.family = family;
    }

    public void setPitch(final int pitch) {
        PrimitiveUtil.checkUnsignedShort(pitch);
        this.pitch = pitch;
    }

    public void setWeigth(final int weigth) {
        PrimitiveUtil.checkUnsignedShort(weigth);
        this.weigth = weigth;
    }

    public void setUnderline(final int underline) {
        PrimitiveUtil.checkUnsignedShort(underline);
        this.underline = underline;
    }

    public void setStrikeout(final int strikeout) {
        PrimitiveUtil.checkUnsignedShort(strikeout);
        this.strikeout = strikeout;
    }

    public void setItalic(final int italic) {
        PrimitiveUtil.checkUnsignedShort(italic);
        this.italic = italic;
    }

    public void setLanguage(final int language) {
        PrimitiveUtil.checkUnsignedShort(language);
        this.language = language;
    }

    public void setWidth(final int width) {
        PrimitiveUtil.checkUnsignedShort(width);
        this.width = width;
    }

    public void setOrientation(final int orientation) {
        PrimitiveUtil.checkUnsignedShort(orientation);
        this.orientation = orientation;
    }

    public void setWordline(final boolean wordline) {
        this.wordline = wordline;
    }

    public void setOutline(final boolean outline) {
        this.outline = outline;
    }

    public void setShadow(final boolean shadow) {
        this.shadow = shadow;
    }

    public void setKerning(final byte kerning) {
        this.kerning = kerning;
    }

    public void setRelief(final byte relief) {
        this.relief = relief;
    }

    public void setCJKLanguage(final int cjkLanguage) {
        PrimitiveUtil.checkUnsignedShort(cjkLanguage);
        this.cjkLanguage = cjkLanguage;
    }

    public void setVertical(final boolean vertical) {
        this.vertical = vertical;
    }

    public void setEmphasisMark(final int emphasis) {
        PrimitiveUtil.checkUnsignedShort(emphasis);
        this.emphasisMark = emphasis;
    }

    public void setOverline(final int overline) {
        PrimitiveUtil.checkUnsignedShort(overline);
        this.overline = overline;
    }

    @Override
    public int getVersion() {
        return 3;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        // TODO: replace?
        // out.writeUnicodeOrAsciiString(familyName);
        // out.writeUnicodeOrAsciiString(styleName);
        out.writeUnsignedShortPrefixedAsciiString(this.familyName);
        out.writeUnsignedShortPrefixedAsciiString(this.styleName);
        out.writeSize(this.size);
        out.writeUnsignedShort(this.charset);
        out.writeUnsignedShort(this.family);
        out.writeUnsignedShort(this.pitch);
        out.writeUnsignedShort(this.weigth);
        out.writeUnsignedShort(this.underline);
        out.writeUnsignedShort(this.strikeout);
        out.writeUnsignedShort(this.italic);
        out.writeUnsignedShort(this.language);
        out.writeUnsignedShort(this.width);
        out.writeUnsignedShort(this.orientation);
        out.writeBoolean(this.wordline);
        out.writeBoolean(this.outline);
        out.writeBoolean(this.shadow);
        out.writeByte(this.kerning);

        // version 2
        out.writeByte(this.relief);
        out.writeUnsignedShort(this.cjkLanguage);
        out.writeBoolean(this.vertical);
        out.writeUnsignedShort(this.emphasisMark);

        // version 3
        out.writeUnsignedShort(this.overline);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        // TODO: replace?
        // familyName = in.readUnicodeOrAsciiString();
        // styleName = in.readUnicodeOrAsciiString();
        this.familyName = in.readUnsignedShortPrefixedAsciiString();
        this.styleName = in.readUnsignedShortPrefixedAsciiString();
        this.size = in.readSize();
        this.charset = in.readUnsignedShort();
        this.family = in.readUnsignedShort();
        this.pitch = in.readUnsignedShort();
        this.weigth = in.readUnsignedShort();
        this.underline = in.readUnsignedShort();
        this.strikeout = in.readUnsignedShort();
        this.italic = in.readUnsignedShort();
        this.language = in.readUnsignedShort();
        this.width = in.readUnsignedShort();
        this.orientation = in.readUnsignedShort();
        this.wordline = in.readBoolean();
        this.outline = in.readBoolean();
        this.shadow = in.readBoolean();
        this.kerning = in.readByte();

        if (version >= 2) {
            this.relief = in.readByte();
            this.cjkLanguage = in.readUnsignedShort();
            this.vertical = in.readBoolean();
            this.emphasisMark = in.readUnsignedShort();

            if (version >= 3) {
                this.overline = in.readUnsignedShort();
            }
        }
    }

}