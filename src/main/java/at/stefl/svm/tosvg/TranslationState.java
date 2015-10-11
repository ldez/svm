package at.stefl.svm.tosvg;

import at.stefl.svm.object.Color;
import at.stefl.svm.object.basic.FontDefinition;
import at.stefl.svm.object.basic.MapMode;

public class TranslationState {

    private MapMode mapMode;

    private FontDefinition fontDefinition;

    private Color lineColor;
    private Color fillColor;
    private Color textColor = new Color(0);

    public MapMode getMapMode() {
        return this.mapMode;
    }

    public FontDefinition getFontDefinition() {
        return this.fontDefinition;
    }

    public Color getLineColor() {
        return this.lineColor;
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public Color getTextColor() {
        return this.textColor;
    }

    public void setMapMode(final MapMode mapMode) {
        this.mapMode = mapMode;
    }

    public void setFontDefinition(final FontDefinition fontDefinition) {
        this.fontDefinition = fontDefinition;
    }

    public void setLineColor(final Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setFillColor(final Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setTextColor(final Color textColor) {
        if (textColor == null) {
            throw new NullPointerException();
        }

        this.textColor = textColor;
    }

}