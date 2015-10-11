package at.stefl.svm.tosvg.action;

import java.io.IOException;

import at.stefl.svm.object.action.MapModeAction;
import at.stefl.svm.tosvg.SVGStateWriter;
import at.stefl.svm.tosvg.TranslationState;

public class MapModeTranslator extends SVGActionTranslator<MapModeAction> {

    public static final MapModeTranslator TRANSLATOR = new MapModeTranslator();

    private MapModeTranslator() {
        super(MapModeAction.class);
    }

    @Override
    protected void translateImpl(final MapModeAction action, final SVGStateWriter out, final TranslationState state) throws IOException {
        state.setMapMode(action.getMapMode());
    }

}