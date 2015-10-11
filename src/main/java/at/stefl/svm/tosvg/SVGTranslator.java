package at.stefl.svm.tosvg;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import at.stefl.commons.io.CloseableOutputStream;
import at.stefl.commons.lwxml.writer.LWXMLStreamWriter;
import at.stefl.svm.io.SVMReader;
import at.stefl.svm.object.SVMHeader;
import at.stefl.svm.object.action.SVMAction;
import at.stefl.svm.tosvg.action.FillColorActionTranslator;
import at.stefl.svm.tosvg.action.FontActionTranslator;
import at.stefl.svm.tosvg.action.LineColorActionTranslator;
import at.stefl.svm.tosvg.action.MapModeTranslator;
import at.stefl.svm.tosvg.action.PolyLineActionTranslator;
import at.stefl.svm.tosvg.action.PolyPolygonActionTranslator;
import at.stefl.svm.tosvg.action.PolygonActionTranslator;
import at.stefl.svm.tosvg.action.RectangleActionTranslator;
import at.stefl.svm.tosvg.action.SVGActionTranslator;
import at.stefl.svm.tosvg.action.TextActionTranslator;
import at.stefl.svm.tosvg.action.TextArrayActionTranslator;
import at.stefl.svm.tosvg.action.TextColorActionTranslator;

public class SVGTranslator {

    public static final SVGTranslator TRANSLATOR = new SVGTranslator();

    private final Map<Class<? extends SVMAction>, SVGActionTranslator<? extends SVMAction>> translatorMap = new HashMap<Class<? extends SVMAction>, SVGActionTranslator<? extends SVMAction>>();

    private SVGTranslator() {
        this.addTranslator(MapModeTranslator.TRANSLATOR);

        this.addTranslator(FillColorActionTranslator.TRANSLATOR);
        this.addTranslator(LineColorActionTranslator.TRANSLATOR);
        this.addTranslator(TextColorActionTranslator.TRANSLATOR);

        this.addTranslator(RectangleActionTranslator.TRANSLATOR);
        this.addTranslator(PolyLineActionTranslator.TRANSLATOR);
        this.addTranslator(PolygonActionTranslator.TRANSLATOR);
        this.addTranslator(PolyPolygonActionTranslator.TRANSLATOR);

        this.addTranslator(FontActionTranslator.TRANSLATOR);

        this.addTranslator(TextActionTranslator.TRANSLATOR);
        this.addTranslator(TextArrayActionTranslator.TRANSLATOR);
    }

    public void addTranslator(final SVGActionTranslator<? extends SVMAction> translator) {
        this.translatorMap.put(translator.getActionClass(), translator);
    }

    public void removeTranslator(final Class<? extends SVMAction> actionClass) {
        this.translatorMap.remove(actionClass);
    }

    public void removeTranslator(final SVGActionTranslator<? extends SVMAction> translator) {
        this.removeTranslator(translator.getActionClass());
    }

    public void translate(final InputStream in, final OutputStream out) throws IOException {
        final SVMReader reader = new SVMReader(in);
        // TODO: closeable?
        final SVGStateWriter writer = new SVGStateWriter(new LWXMLStreamWriter(new CloseableOutputStream(out)));

        final SVMHeader header = reader.readHeader();

        final TranslationState state = new TranslationState();
        state.setMapMode(header.getMapMode());

        writer.writeHeader();
        writer.writeAttribute("viewBox", "0 0 " + header.getSize().getX() + " " + header.getSize().getY());

        SVMAction action;
        while ((action = reader.readAction()) != null) {
            final Class<? extends SVMAction> actionClass = action.getClass();
            final SVGActionTranslator<? extends SVMAction> translator = this.translatorMap.get(actionClass);
            if (translator == null) {
                continue;
            }
            translator.translate(action, writer, state);
        }

        writer.writeFooter();
        writer.flush();
        writer.close();
    }

}