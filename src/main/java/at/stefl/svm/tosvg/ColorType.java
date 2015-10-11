package at.stefl.svm.tosvg;

import java.io.IOException;
import java.io.Writer;

import at.stefl.svm.object.Color;

// TODO: implement and use property writer
public enum ColorType {

    STROKE, FILL;

    private static final String OPACITY_ELEMENT_SUFFIX = "-opacity";

    private final String colorProperty;
    private final String opacityProperty;

    private ColorType() {
        this.colorProperty = this.name().toLowerCase();
        this.opacityProperty = this.colorProperty + OPACITY_ELEMENT_SUFFIX;
    }

    public String getColorProperty() {
        return this.colorProperty;
    }

    public String getOpacityProperty() {
        return this.opacityProperty;
    }

    public void writeProperty(final Color color, final Writer out) throws IOException {
        out.write(this.colorProperty);

        if (color == null) {
            out.write(OPACITY_ELEMENT_SUFFIX);
            out.write(":0");
        } else {
            out.write(":");
            out.write(SVGUtil.getColorAttribute(color));
        }

        out.write(";");
    }

}